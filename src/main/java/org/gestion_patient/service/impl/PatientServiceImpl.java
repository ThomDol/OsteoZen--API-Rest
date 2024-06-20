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
        Patient patientToCreate = patientRepository.findByIdentiteNomAndIdentitePrenomAndDateNaissanceAndPraticienIdPraticien(Crypto.cryptService(patientDto.getNomPatient()),Crypto.cryptService(patientDto.getPrenomPatient()),Crypto.cryptService(patientDto.getDateNaissance()),idPraticienConnecte);
        if(patientToCreate!=null){throw new RessourceAlreadyexistsException("Patient already exists fot this praticien with nom and birth date, or by email");}
        else{
            //Verification si identité de la personne déjà enregistrée, si oui l'utilise. Sinon creation (Cryptage des données Dto avant comparasion, car données cryptées dans la base)
            Personne personneIdNewPatient = personneRepository.findByNomAndPrenom(Crypto.cryptService(patientDto.getNomPatient()),Crypto.cryptService(patientDto.getPrenomPatient()));
            if (personneIdNewPatient==null){
                Personne personneToSave = new Personne();
                personneToSave.setNom(Crypto.cryptService(patientDto.getNomPatient()));
                personneToSave.setPrenom(Crypto.cryptService(patientDto.getPrenomPatient()));
                if(patientDto.getEmail()!=null){personneToSave.setEmail(Crypto.cryptService(patientDto.getEmail()));}
                if(patientDto.getTel()!=null){personneToSave.setTel(Crypto.cryptService(patientDto.getTel()));}
                personneIdNewPatient= personneRepository.save(personneToSave);}
            //genre, typePatient, seront récupérés dans le front
            Genre genre = genreRepository.findByNomGenre(patientDto.getNomGenre());
            TypePatient typePatient = typePatientRepository.findTypePatientByNomTypePatient(patientDto.getNomTypePatient());
            //Lieu recupéré si saisi ds le front via API BAN, et enregistré ds la bdd si pas encore fait
            Lieu lieu;
            if(patientDto.getNomVille()!=null && patientDto.getCodePostal()!=null){
                lieu = lieuRepository.findByNomVilleAndCodePostal(patientDto.getNomVille(),patientDto.getCodePostal());
                if(lieu==null){
                    lieu = new Lieu();
                    lieu.setNomVille(patientDto.getNomVille());
                    lieu.setCodePostal(patientDto.getCodePostal());
                    lieuRepository.save(lieu);}
            }
            else{lieu = null;}
            //Profession recupéré via le front si saisie
            Profession profession;
            if(patientDto.getNomProfession()!=null){profession = professionRepository.findByLibelleProfession(patientDto.getNomProfession());}
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
    public PatientDto getById(int id) throws Exception {
        Patient patient = patientRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Patient not found with given Id"+id));
        return PatientMapper.mapToPatientDto(patient);
    }

    @Override
    public PatientDto getByIdAndIdPraticien(int id,int idPraticien) throws Exception {
        Patient patient = patientRepository.findByIdPatientAndPraticienIdPraticien(id,idPraticien);
        if(patient!=null){
        return PatientMapper.mapToPatientDto(patient);}
    return null;}


    @Override
    public void deletePatient(int id) {
        Patient patient = patientRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Patient not found with given Id"+id));
        patientRepository.delete(patient);
    }

    @Override
    public PatientDto updatePatient(int id, PatientDto upadtedPatientDto, int idPraticien) throws Exception {
        Patient patientToUpdate = patientRepository.findByIdPatientAndPraticienIdPraticien(id,idPraticien);
        //Set seulement des infos dont les données ont été remplies ds le formulaire
        if(upadtedPatientDto.getDateNaissance()!=null){patientToUpdate.setDateNaissance(Crypto.cryptService(upadtedPatientDto.getDateNaissance()));}
        //Récupération de l'identité actuellement sauvegardée, avant modification par les données du formulaire
        Personne personneToUpdate = patientToUpdate.getIdentite();
        //Modification si besoin
        if(upadtedPatientDto.getNomPatient()!=null){
            personneToUpdate.setNom(Crypto.cryptService(upadtedPatientDto.getNomPatient()));}
        if(upadtedPatientDto.getPrenomPatient()!=null){
            personneToUpdate.setPrenom(Crypto.cryptService(upadtedPatientDto.getPrenomPatient()));}
        if(upadtedPatientDto.getTel()!=null){
            personneToUpdate.setTel(Crypto.cryptService(upadtedPatientDto.getTel()));}
        if(upadtedPatientDto.getEmail()!=null){
            personneToUpdate.setEmail(Crypto.cryptService(upadtedPatientDto.getEmail()));}
        patientToUpdate.setIdentite(personneRepository.save(personneToUpdate));

        //Mofif Genre si besoin
        if(upadtedPatientDto.getNomGenre()!=null){
            Genre genreToUpdate = genreRepository.findByNomGenre(upadtedPatientDto.getNomGenre());
            patientToUpdate.setGenre(genreToUpdate);
        }
        //Modif TypePatient
        if(upadtedPatientDto.getNomTypePatient()!=null){
            TypePatient type = typePatientRepository.findTypePatientByNomTypePatient(upadtedPatientDto.getNomTypePatient());
            patientToUpdate.setTypePatient(type);
        }

        //Mise à jour du lieu si besoin
        Lieu lieu;
        if(upadtedPatientDto.getCodePostal()!=null && upadtedPatientDto.getNomVille()!=null){
            lieu = lieuRepository.findByNomVilleAndCodePostal(upadtedPatientDto.getNomVille(),upadtedPatientDto.getCodePostal());
            if(lieu==null){
                lieu = new Lieu();
                lieu.setNomVille(upadtedPatientDto.getNomVille());
                lieu.setCodePostal(upadtedPatientDto.getCodePostal());
                lieuRepository.save(lieu);}
            patientToUpdate.setVille(lieu);
        }
        if(upadtedPatientDto.getNomProfession()!=null){patientToUpdate.setProfession(professionRepository.findByLibelleProfession(upadtedPatientDto.getNomProfession()));}
        if(upadtedPatientDto.getNomMedecinTraitant()!=null && upadtedPatientDto.getPrenomMedecinTraitant()!=null && upadtedPatientDto.getVilleMedecinTraitant()!=null){patientToUpdate.setMedecinTraitant(medecintraitantRepository.findByIdentiteDocNomAndIdentiteDocPrenomAndLieuNomVille(Crypto.cryptService(upadtedPatientDto.getNomMedecinTraitant()),Crypto.cryptService(upadtedPatientDto.getPrenomMedecinTraitant()),upadtedPatientDto.getVilleMedecinTraitant()));}

        return PatientMapper.mapToPatientDto(patientRepository.save(patientToUpdate));
    }

}