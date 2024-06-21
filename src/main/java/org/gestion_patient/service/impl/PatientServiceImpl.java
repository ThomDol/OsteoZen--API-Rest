package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.*;
import org.gestion_patient.entityDto.PatientDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.exception.RessourceAlreadyexistsException;
import org.gestion_patient.mapper.PatientMapper;
import org.gestion_patient.repository.*;
import org.gestion_patient.service.PatientService;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;
    private LieuRepository lieuRepository;
    private GenreRepository genreRepository;
    private ProfessionRepository professionRepository;
    private TypePatientRepository typePatientRepository;
    private MedecintraitantRepository medecintraitantRepository;
    private PersonneRepository personneRepository;
    private PraticienRepository praticienconnecteRepository;



    @Override
    public List<PatientDto> getAllPatient()  {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patient->
        {
            try {return PatientMapper.mapToPatientDto(patient);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    //Creation d'un nouveau Patient
    @Override
    public PatientDto createPatient(PatientDto patientDto,int idPraticienConnecte) throws Exception {
        //Verification si Patient existe déjà avec ce praticien (Cryptage des données Dto avant comparasion, car données cryptées dans la base)
        Patient patientToCreate = patientRepository.findByIdentiteNomAndIdentitePrenomAndDateNaissanceAndIdentiteTelAndPraticienIdPraticien(Crypto.cryptService(patientDto.getNomPatient().toUpperCase()),Crypto.cryptService(patientDto.getPrenomPatient().toUpperCase()),Crypto.cryptService(patientDto.getDateNaissance()),Crypto.cryptService(patientDto.getTel()),idPraticienConnecte);
        if(patientToCreate!=null){throw new RessourceAlreadyexistsException("Patient already exists fot this praticien with nom and birth date");}
        else{
            //Verification si identité de la personne déjà enregistrée, si oui l'utilise. Sinon creation (Cryptage des données Dto avant comparasion, car données cryptées dans la base)
            Personne personneIdNewPatient = personneRepository.findByNomAndPrenomAndTel(Crypto.cryptService(patientDto.getNomPatient().toUpperCase()),Crypto.cryptService(patientDto.getPrenomPatient().toUpperCase()),Crypto.cryptService(patientDto.getTel()));
            if (personneIdNewPatient==null){
                personneIdNewPatient = new Personne();
                personneIdNewPatient.setNom(Crypto.cryptService(patientDto.getNomPatient().toUpperCase()));
                personneIdNewPatient.setPrenom(Crypto.cryptService(patientDto.getPrenomPatient().toUpperCase()));
                if(patientDto.getEmail()!=null){personneIdNewPatient.setEmail(Crypto.cryptService(patientDto.getEmail()));}
                personneIdNewPatient.setTel(Crypto.cryptService(patientDto.getTel()));
              personneRepository.save(personneIdNewPatient);}
            //genre, typePatient, seront récupérés dans le front
            Genre genre = genreRepository.findByNomGenre(patientDto.getNomGenre());
            TypePatient typePatient = typePatientRepository.findByNomTypePatient(patientDto.getNomTypePatient());
            //Lieu recupéré ds le front et enregistré ds la bdd si pas encore dedans
            Lieu lieu;
            if(patientDto.getNomVille()!=null && patientDto.getCodePostal()!=null){
                lieu = lieuRepository.findByNomVilleAndCodePostal(patientDto.getNomVille(),patientDto.getCodePostal());
                if(lieu==null){
                    lieu = new Lieu();
                    lieu.setNomVille(patientDto.getNomVille().toUpperCase());
                    lieu.setCodePostal(patientDto.getCodePostal());
                    lieuRepository.save(lieu);}
            }
            else{lieu = null;}
            //Profession recupérée et enregistrée ds la bdd si pas encore dedans
            Profession profession;
            if(patientDto.getNomProfession()!=null){
                profession = professionRepository.findByLibelleProfession(patientDto.getNomProfession());
                if (profession==null){
                    profession = new Profession();
                    profession.setLibelleProfession(patientDto.getNomProfession().toUpperCase());
                    professionRepository.save(profession);}

            }
            else{profession =null;}
            //Medecin traitant récupéré ds le front si saisi, et enregistré en bdd si pas déjà dedans, avant soumission de cette requete
            Medecintraitant medecintraitant;
            if(patientDto.getNomMedecinTraitant()!=null && patientDto.getPrenomMedecinTraitant()!=null){medecintraitant = medecintraitantRepository.findByIdentiteDocNomAndIdentiteDocPrenomAndLieuNomVille(Crypto.cryptService(patientDto.getNomMedecinTraitant()), Crypto.cryptService(patientDto.getPrenomMedecinTraitant()),patientDto.getVilleMedecinTraitant());}
            else{medecintraitant=null;}
            //Persisitence du patient ds la base de données
            Praticien praticienconnecte = praticienconnecteRepository.findById(idPraticienConnecte).orElseThrow(() -> new ResourceNotFoundException("Praticien not found with given Id" + idPraticienConnecte));
            Patient patientTSave = PatientMapper.mapToPatient(patientDto, lieu, genre, profession, typePatient, medecintraitant, personneIdNewPatient, praticienconnecte);
            return PatientMapper.mapToPatientDto(patientRepository.save(patientTSave));}
    }


    @Override
    public List<PatientDto> getAllPatientByPraticien(int idPraticien) {
        Praticien praticien= praticienconnecteRepository.findById(idPraticien).orElseThrow(()->new ResourceNotFoundException("Praticien not found with given Id"+idPraticien));
        List<Patient> patients = patientRepository.findAllByPraticien(praticien);

        return patients.stream().map(patient-> {
            try {
                return PatientMapper.mapToPatientDto(patient);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }


    @Override
    public PatientDto getByIdAndIdPraticien(int id,int idPraticien) throws Exception {
        Patient patient = patientRepository.findByIdPatientAndPraticienIdPraticien(id, idPraticien);
        if (patient != null) {
            return PatientMapper.mapToPatientDto(patient);
        } else {
            throw new ResourceNotFoundException("Patient not found");
        }
    }

    @Override
    public void deletePatientByPraticien(int id,int idPraticien) {
        Patient patient = patientRepository.findByIdPatientAndPraticienIdPraticien(id,idPraticien);
        if(patient!=null){patientRepository.delete(patient);}
        else {throw new ResourceNotFoundException("Patient not found");}
    }

    @Override
    public PatientDto updatePatient(int id, PatientDto updatedPatientDto, int idPraticien) throws Exception {
        Patient patientToUpdate = patientRepository.findByIdPatientAndPraticienIdPraticien(id, idPraticien);
        if (patientToUpdate != null) {
            //Set seulement des infos dont les données ont été remplies ds le formulaire
            if (updatedPatientDto.getDateNaissance() != null) {
                patientToUpdate.setDateNaissance(Crypto.cryptService(updatedPatientDto.getDateNaissance()));
            }
            //Récupération de l'identité actuellement sauvegardée, avant modification par les données du formulaire
            Personne personneToUpdate = patientToUpdate.getIdentite();
            //Modification si besoin
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

            //Mofif Genre si besoin
            if (updatedPatientDto.getNomGenre() != null) {
                Genre genreToUpdate = genreRepository.findByNomGenre(updatedPatientDto.getNomGenre());
                patientToUpdate.setGenre(genreToUpdate);
            }
            //Modif TypePatient
            if (updatedPatientDto.getNomTypePatient() != null) {
                TypePatient type = typePatientRepository.findByNomTypePatient(updatedPatientDto.getNomTypePatient());
                patientToUpdate.setTypePatient(type);
            }

            //Mise à jour du lieu si besoin
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

            //Mise à jour profession si saisie
            if (updatedPatientDto.getNomProfession() != null) {
                Profession profession = professionRepository.findByLibelleProfession(updatedPatientDto.getNomProfession().toUpperCase());
                if (profession == null) {
                    profession = new Profession();
                    profession.setLibelleProfession(updatedPatientDto.getNomProfession().toUpperCase());
                professionRepository.save(profession);}
                patientToUpdate.setProfession(profession);
                }

            if (updatedPatientDto.getNomMedecinTraitant() != null && updatedPatientDto.getPrenomMedecinTraitant() != null && updatedPatientDto.getVilleMedecinTraitant() != null) {
                patientToUpdate.setMedecinTraitant(medecintraitantRepository.findByIdentiteDocNomAndIdentiteDocPrenomAndLieuNomVille(Crypto.cryptService(updatedPatientDto.getNomMedecinTraitant()), Crypto.cryptService(updatedPatientDto.getPrenomMedecinTraitant()), updatedPatientDto.getVilleMedecinTraitant()));
            }

            return PatientMapper.mapToPatientDto(patientRepository.save(patientToUpdate));
        } else {
            throw new ResourceNotFoundException("Patient not found");
        }
    }


}