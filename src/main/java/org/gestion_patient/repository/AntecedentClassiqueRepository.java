package org.gestion_patient.repository;
import org.gestion_patient.entity.AntecedentClassique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AntecedentClassiqueRepository extends JpaRepository<AntecedentClassique,Integer> {

    AntecedentClassique findByIdAntecedentClassiqueAndPatientIdPatient (int id, int patientId);
}