package org.gestion_patient.repository;

import org.gestion_patient.entity.Praticien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PraticienRepository extends JpaRepository<Praticien,Integer> {
        Praticien findByIdentiteEmail(String email);
    }