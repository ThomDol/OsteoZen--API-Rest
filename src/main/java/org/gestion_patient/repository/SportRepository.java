package org.gestion_patient.repository;

import org.gestion_patient.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SportRepository extends JpaRepository<Sport,Integer> {
    public Sport findByNomSport(String name);
}
