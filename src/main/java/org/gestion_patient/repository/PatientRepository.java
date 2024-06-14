package org.gestion_patient.repository;

import org.gestion_patient.entity.Patient;
import org.gestion_patient.entity.Praticien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
    List<Patient> findAllByPraticien (Praticien praticien);
    Patient findByIdentiteNomAndIdentitePrenomAndDateNaissanceAndPraticienIdPraticien(String nom,String prenom,String dadeNaissance,int idPraticien);

}
