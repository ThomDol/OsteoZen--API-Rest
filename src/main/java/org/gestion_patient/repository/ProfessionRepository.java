package org.gestion_patient.repository;

import org.gestion_patient.entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession,Integer> {
    Profession findByLibelleProfession(String libelleProfession);
}
