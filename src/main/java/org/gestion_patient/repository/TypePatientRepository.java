package org.gestion_patient.repository;

import org.gestion_patient.entity.TypePatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePatientRepository extends JpaRepository<TypePatient,Integer> {
    TypePatient findByNomTypePatient(String nom );
}
