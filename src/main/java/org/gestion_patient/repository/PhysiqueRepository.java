package org.gestion_patient.repository;

import org.gestion_patient.entity.Physique;
import org.gestion_patient.entityDto.PhysiqueDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysiqueRepository extends JpaRepository<Physique,Integer> {
    List<Physique> findAllByPatientIdPatient(int idPatient);
}
