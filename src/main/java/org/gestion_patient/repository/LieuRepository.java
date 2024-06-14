package org.gestion_patient.repository;

import org.gestion_patient.entity.Lieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LieuRepository extends JpaRepository<Lieu,Integer> {
    Lieu findByNomVilleAndCodePostal(String nomVille,String codePotal);
    Lieu findByNomVille(String nomVille);
}
