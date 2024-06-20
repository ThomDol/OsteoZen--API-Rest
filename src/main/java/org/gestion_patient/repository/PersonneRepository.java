package org.gestion_patient.repository;

import org.gestion_patient.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepository extends JpaRepository<Personne,Integer> {

    Personne findByNomAndPrenom(String nom ,String prenom);
    Personne findByNomAndPrenomAndTel(String nom ,String prenom,String tel);


}
