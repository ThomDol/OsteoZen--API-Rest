package org.gestion_patient.repository;

import org.gestion_patient.entity.AntecedentsBebe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AntecedentBebeRepository  extends JpaRepository<AntecedentsBebe,Integer> {
    AntecedentsBebe findByPatientIdPatient(int id );
}
