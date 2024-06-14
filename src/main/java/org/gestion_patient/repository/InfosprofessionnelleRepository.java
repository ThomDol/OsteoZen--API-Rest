package org.gestion_patient.repository;

import org.gestion_patient.entity.Infosprofessionnelles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfosprofessionnelleRepository extends JpaRepository<Infosprofessionnelles,Integer> {
    Infosprofessionnelles findByNumAdeliAndNumSiret(String numAdeli,String numSiret);
}
