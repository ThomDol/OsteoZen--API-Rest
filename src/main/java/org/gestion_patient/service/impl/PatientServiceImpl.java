package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.*;
import org.gestion_patient.entityDto.PatientDto;
import org.gestion_patient.exception.AccountBlockedException;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.exception.RessourceAlreadyexistsException;
import org.gestion_patient.mapper.PatientMapper;
import org.gestion_patient.repository.*;
import org.gestion_patient.service.PatientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Annotation pour définir un service Spring et injection des dépendances via le constructeur
@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PostAccouchementRepository postAccouchementRepository;
    private final GrossesseRepository grossesseRepository;
    private final PhysiqueRepository physiqueRepository;
    private PatientRepository patientRepository;
    private LieuRepository lieuRepository;
    private GenreRepository genreRepository;
    private ProfessionRepository professionRepository;
    private TypePatientRepository typePatientRepository;
    private MedecintraitantRepository medecintraitantRepository;
    private PersonneRepository personneRepository;
    private AppUserRepository appUserRepository;
    private AccouchementRepository accouchementRepository;
    private AntecedentClassiqueRepository antecedentClassiqueRepository;
    private AntecedentBebeRepository antecedentBebeRepository;
    private RendezvousRepository rendezvousRepository;


    // Création d'un nouveau Patient
    @Override
    public PatientDto createPatient(PatientDto patientDto, int idAppUser) throws Exception {
        // Vérification si le patient existe déjà pour ce praticien en cryptant les données avant la comparaison
        Patient patientToCreate = patientRepository.findByIdentiteNomAndIdentitePrenomAndDateNaissanceAndIdentiteTelAndAppUserIdAppUser(
                Crypto.cryptService(patientDto.getNomPatient().toUpperCase()),
                Crypto.cryptService(patientDto.getPrenomPatient().toUpperCase()),
                Crypto.cryptService(patientDto.getDateNaissance()),
                Crypto.cryptService(patientDto.getTel()), idAppUser);

        if (patientToCreate != null) {
            throw new RessourceAlreadyexistsException("Patient already exists for this AppUser");
        } else {
            // Vérification de l'identité de la personne enregistrée (pourrait être un patient d'un autre praticien). L'email est facultatif.
            Personne personneIdNewPatient = personneRepository.findByNomAndPrenomAndTel(
                    Crypto.cryptService(patientDto.getNomPatient().toUpperCase()),
                    Crypto.cryptService(patientDto.getPrenomPatient().toUpperCase()),
                    Crypto.cryptService(patientDto.getTel()));

            if (personneIdNewPatient == null) {
                personneIdNewPatient = new Personne();
                personneIdNewPatient.setNom(Crypto.cryptService(patientDto.getNomPatient().toUpperCase()));
                personneIdNewPatient.setPrenom(Crypto.cryptService(patientDto.getPrenomPatient().toUpperCase()));
                if (patientDto.getEmail() != null) {
                    personneIdNewPatient.setEmail(Crypto.cryptService(patientDto.getEmail()));
                }
                personneIdNewPatient.setTel(Crypto.cryptService(patientDto.getTel()));
                personneRepository.save(personneIdNewPatient);
            }

            // Genre et typePatient récupérés dans le front
            Genre genre = genreRepository.findByNomGenre(patientDto.getNomGenre());
            TypePatient typePatient = typePatientRepository.findByNomTypePatient(patientDto.getNomTypePatient());

            // Lieu récupéré du front et enregistré dans la bdd si pas encore dedans
            Lieu lieu;
            if (patientDto.getNomVille() != null && patientDto.getCodePostal() != null) {
                lieu = lieuRepository.findByNomVilleAndCodePostal(patientDto.getNomVille().toUpperCase(), patientDto.getCodePostal());
                if (lieu == null) {
                    lieu = new Lieu();
                    lieu.setNomVille(patientDto.getNomVille().toUpperCase());
                    lieu.setCodePostal(patientDto.getCodePostal());
                    lieuRepository.save(lieu);
                }
            } else {
                lieu = null;
            }

            // Profession récupérée et enregistrée dans la bdd si pas encore dedans
            Profession profession;
            if (patientDto.getNomProfession() != null) {
                profession = professionRepository.findByLibelleProfession(patientDto.getNomProfession().toUpperCase());
                if (profession == null) {
                    profession = new Profession();
                    profession.setLibelleProfession(patientDto.getNomProfession().toUpperCase());
                    professionRepository.save(profession);
                }
            } else {
                profession = null;
            }

            // Médecin traitant récupéré du front si saisi. (facultatif). Creation ds le front via une autre requete d'un nouveau MedecinTraitant
            Medecintraitant medecintraitant;
            if (patientDto.getNomMedecinTraitant() != null && patientDto.getPrenomMedecinTraitant() != null && patientDto.getVilleMedecinTraitant() != null && patientDto.getCodePostalMedecinTraitant() != null) {
                medecintraitant = medecintraitantRepository.findByIdentiteDocNomAndIdentiteDocPrenomAndLieuNomVilleAndLieuCodePostal(
                        Crypto.cryptService(patientDto.getNomMedecinTraitant()),
                        Crypto.cryptService(patientDto.getPrenomMedecinTraitant()),
                        patientDto.getVilleMedecinTraitant(),
                        patientDto.getCodePostalMedecinTraitant());
            } else {
                medecintraitant = null;
            }

            // Persistance du patient dans la base de données
            AppUser appUser = appUserRepository.findById(idAppUser).orElseThrow(() -> new ResourceNotFoundException("AppUser not found with given Id" + idAppUser));
            Patient patientTSave = PatientMapper.mapToPatient(patientDto, lieu, genre, profession, typePatient, medecintraitant, personneIdNewPatient, appUser);
            return PatientMapper.mapToPatientDto(patientRepository.save(patientTSave));
        }
    }

    // Récupération de tous les patients d'un praticien
    @Override
    public List<PatientDto> getAllPatientByAppUser(int idAppUser) {
        AppUser appUser = appUserRepository.findById(idAppUser).orElseThrow(() -> new ResourceNotFoundException("AppUser not found with given Id" + idAppUser));
        if (appUser.isActive()) {
        List<Patient> patients = patientRepository.findAllByAppUser(appUser);
        return patients.stream().map(patient -> {
            try {
                return PatientMapper.mapToPatientDto(patient);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();}
        else{
            throw new AccountBlockedException("Account temporary blocked");
        }
    }

    // Récupération d'un patient par son ID
    @Override
    public PatientDto getById(int id) throws Exception {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient not found with given Id" + id));
        return PatientMapper.mapToPatientDto(patient);
    }

    // Récupération d'un patient par son ID et celui du praticien
    @Override
    public PatientDto getByIdAndIdAppUser(int id, int idAppUser) throws Exception {
        AppUser appUser = appUserRepository.findById(idAppUser).orElseThrow(() -> new ResourceNotFoundException("AppUser not found"));
        if (appUser.isActive()) {
            Patient patient = patientRepository.findByIdPatientAndAppUserIdAppUser(id, idAppUser);
            if (patient != null) {
                return PatientMapper.mapToPatientDto(patient);
            } else {
                throw new ResourceNotFoundException("Patient not found");
            }
        } else {
            throw new AccountBlockedException("Account temporary blocked");
        }
    }

    // Mise à jour d'un patient
    @Override
    public PatientDto updatePatient(int id, PatientDto updatedPatientDto) throws Exception {
        Patient patientToUpdate = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        // Mise à jour des informations du patient
        if (updatedPatientDto.getDateNaissance() != null) {
            patientToUpdate.setDateNaissance(Crypto.cryptService(updatedPatientDto.getDateNaissance()));
        }

        // Mise à jour de l'identité de la personne
        Personne personneToUpdate = patientToUpdate.getIdentite();
        if (updatedPatientDto.getNomPatient() != null) {
            personneToUpdate.setNom(Crypto.cryptService(updatedPatientDto.getNomPatient().toUpperCase()));
        }
        if (updatedPatientDto.getPrenomPatient() != null) {
            personneToUpdate.setPrenom(Crypto.cryptService(updatedPatientDto.getPrenomPatient().toUpperCase()));
        }
        if (updatedPatientDto.getTel() != null) {
            personneToUpdate.setTel(Crypto.cryptService(updatedPatientDto.getTel()));
        }
        if (updatedPatientDto.getEmail() != null) {
            personneToUpdate.setEmail(Crypto.cryptService(updatedPatientDto.getEmail()));
        }
        patientToUpdate.setIdentite(personneRepository.save(personneToUpdate));

        // Mise à jour du genre si besoin
        if (updatedPatientDto.getNomGenre() != null) {
            Genre genreToUpdate = genreRepository.findByNomGenre(updatedPatientDto.getNomGenre());
            patientToUpdate.setGenre(genreToUpdate);
        }

        // Mise à jour du type de patient
        if (updatedPatientDto.getNomTypePatient() != null) {
            TypePatient type = typePatientRepository.findByNomTypePatient(updatedPatientDto.getNomTypePatient());
            patientToUpdate.setTypePatient(type);
        }

        // Mise à jour du lieu si besoin
        Lieu lieu;
        if (updatedPatientDto.getCodePostal() != null && updatedPatientDto.getNomVille() != null) {
            lieu = lieuRepository.findByNomVilleAndCodePostal(updatedPatientDto.getNomVille(), updatedPatientDto.getCodePostal());
            if (lieu == null) {
                lieu = new Lieu();
                lieu.setNomVille(updatedPatientDto.getNomVille().toUpperCase());
                lieu.setCodePostal(updatedPatientDto.getCodePostal());
                lieuRepository.save(lieu);
            }
            patientToUpdate.setVille(lieu);
        }

        // Mise à jour de la profession si saisie
        if (updatedPatientDto.getNomProfession() != null) {
            Profession profession = professionRepository.findByLibelleProfession(updatedPatientDto.getNomProfession().toUpperCase());
            if (profession == null) {
                profession = new Profession();
                profession.setLibelleProfession(updatedPatientDto.getNomProfession().toUpperCase());
                professionRepository.save(profession);
            }
            patientToUpdate.setProfession(profession);
        }

        // Mise à jour du médecin traitant si besoin
        if (updatedPatientDto.getNomMedecinTraitant() != null && updatedPatientDto.getPrenomMedecinTraitant() != null && updatedPatientDto.getVilleMedecinTraitant() != null && updatedPatientDto.getCodePostalMedecinTraitant() != null) {
            patientToUpdate.setMedecinTraitant(medecintraitantRepository.findByIdentiteDocNomAndIdentiteDocPrenomAndLieuNomVilleAndLieuCodePostal(
                    Crypto.cryptService(updatedPatientDto.getNomMedecinTraitant()),
                    Crypto.cryptService(updatedPatientDto.getPrenomMedecinTraitant()),
                    updatedPatientDto.getVilleMedecinTraitant(),
                    updatedPatientDto.getCodePostalMedecinTraitant()));
        }

        return PatientMapper.mapToPatientDto(patientRepository.save(patientToUpdate));
    }

    // Suppression d'un patient et de ses entités associées dans une transaction
    @Transactional
    @Override
    public void delete(int id) {
        Patient patientToDelete = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        // Suppression des entités associées
        postAccouchementRepository.deleteAllByAccouchementPatientIdPatient(id);
        accouchementRepository.deleteAllByPatientIdPatient(id);
        antecedentClassiqueRepository.deleteByPatientIdPatient(id);
        antecedentBebeRepository.deleteByPatientIdPatient(id);
        rendezvousRepository.deleteAllByPatientIdPatient(id);
        physiqueRepository.deleteAllByPatientIdPatient(id);
        grossesseRepository.deleteAllByPatientIdPatient(id);

        Personne personneToDelete = patientToDelete.getIdentite();

        // Dissocier le patient du praticien
        patientToDelete.setAppUser(null);

        // Suppression du patient
        patientRepository.delete(patientToDelete);
        System.out.println("patient supprimé");

        // Vérification si la personne associée est aussi un autre patient d'un autre praticien, sinon suppression de la personne
        List<Patient> patientList = patientRepository.findAllByIdentite(personneToDelete);
        if (patientList.isEmpty()) {
            personneRepository.delete(personneToDelete);
        }
    }
}
