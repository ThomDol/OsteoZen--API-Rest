package org.gestion_patient.repository;

import org.gestion_patient.entity.Medecintraitant;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entity.AppUser;
import org.gestion_patient.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
    List<Patient> findAllByAppUser (AppUser appUser);
    Patient findByIdentiteNomAndIdentitePrenomAndDateNaissanceAndIdentiteTelAndAppUserIdAppUser(String nom,String prenom,String dadeNaissance,String tel,int idAppUser);
    Patient findByIdPatientAndAppUserIdAppUser(int idPatient,int idAppUser);
    List<Patient> findAllByIdentite (Personne identite);
    List<Patient> findAllByMedecinTraitant (Medecintraitant medecinTraitant);

}
