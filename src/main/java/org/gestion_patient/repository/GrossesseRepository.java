package org.gestion_patient.repository;

import org.gestion_patient.entity.Grossesse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrossesseRepository extends JpaRepository<Grossesse,Integer> {
    List<Grossesse> findByPatientIdPatient (int id);
}
