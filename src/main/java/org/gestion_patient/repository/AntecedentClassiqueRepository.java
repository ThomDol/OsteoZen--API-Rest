package org.gestion_patient.repository;
import org.gestion_patient.entity.AntecedentClassique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AntecedentClassiqueRepository extends JpaRepository<AntecedentClassique,Integer> {
    AntecedentClassique findByPatientIdPatient (int id);
}