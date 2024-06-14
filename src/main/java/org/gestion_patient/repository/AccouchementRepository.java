package org.gestion_patient.repository;

import org.gestion_patient.entity.Accouchement;
import org.gestion_patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccouchementRepository extends JpaRepository<Accouchement,Integer> {
    List<Accouchement> findByPatientIdPatient(int idPatient);
}
