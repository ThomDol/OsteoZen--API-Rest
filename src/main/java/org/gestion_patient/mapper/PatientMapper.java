package org.gestion_patient.mapper;

import org.gestion_patient.Data.DataUtil;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.*;
import org.gestion_patient.entityDto.*;


public class PatientMapper {
    public static PatientDto mapToPatientDto (Patient patient) throws Exception {


        return new PatientDto(
                patient.getIdPatient(),
                Crypto.decryptService(patient.getDateNaissance()),

                patient.getVille()==null?null:patient.getVille().getNomVille(),
                patient.getVille()==null?null:patient.getVille().getCodePostal(),
                patient.getGenre().getNomGenre(),
                patient.getProfession()==null?null:patient.getProfession().getLibelleProfession(),
                patient.getTypePatient().getNomTypePatient(),
                patient.getMedecinTraitant()==null?null: Crypto.decryptService(patient.getMedecinTraitant().getIdentiteDoc().getNom()),
                patient.getMedecinTraitant()==null?null:Crypto.decryptService(patient.getMedecinTraitant().getIdentiteDoc().getPrenom()),
                patient.getMedecinTraitant()==null?null:patient.getMedecinTraitant().getLieu().getNomVille(),
                patient.getMedecinTraitant()==null?null:patient.getMedecinTraitant().getLieu().getCodePostal(),
                Crypto.decryptService(patient.getIdentite().getNom()),
                Crypto.decryptService(patient.getIdentite().getPrenom()),
                patient.getIdentite().getEmail()==null?null:Crypto.decryptService(patient.getIdentite().getEmail()),
                DataUtil.displayStringDecrypt(patient.getIdentite().getTel()),
                patient.getAppUser().getIdAppUser()

        );
    }

    public static Patient mapToPatient(PatientDto patientDto, Lieu lieu, Genre genre , Profession profession , TypePatient typePatient , Medecintraitant medecintraitant , Personne personne, AppUser praticienconnecte) throws Exception {
        Patient patient=new Patient();
        patient.setIdPatient(patientDto.getIdPatient());
        patient.setDateNaissance(Crypto.cryptService(patientDto.getDateNaissance()));
        if(lieu!=null){patient.setVille(lieu);}
        patient.setGenre(genre);
        if(profession!=null){patient.setProfession(profession);}
        patient.setTypePatient(typePatient);
        if(medecintraitant!=null){patient.setMedecinTraitant(medecintraitant);}
        patient.setIdentite(personne);
        patient.setAppUser(praticienconnecte);


        return patient;}



}