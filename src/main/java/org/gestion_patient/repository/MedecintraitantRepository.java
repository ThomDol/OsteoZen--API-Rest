package org.gestion_patient.repository;

import org.gestion_patient.entity.Medecintraitant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecintraitantRepository extends JpaRepository<Medecintraitant,Integer> {
    Medecintraitant findByIdentiteDocNomAndIdentiteDocPrenomAndLieuNomVille (String nom,String prenom,String ville);
}
