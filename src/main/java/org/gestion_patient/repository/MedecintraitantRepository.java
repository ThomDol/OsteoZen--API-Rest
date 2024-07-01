package org.gestion_patient.repository;

import org.gestion_patient.entity.Medecintraitant;
import org.gestion_patient.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedecintraitantRepository extends JpaRepository<Medecintraitant,Integer> {
    Medecintraitant findByIdentiteDocNomAndIdentiteDocPrenomAndLieuNomVilleAndLieuCodePostal (String nom,String prenom,String ville,String codePostal);
    List<Medecintraitant> findAllByIdentiteDoc(Personne identiteDoc);
}
