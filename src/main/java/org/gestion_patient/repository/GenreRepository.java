package org.gestion_patient.repository;

import org.gestion_patient.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Integer> {
    Genre findByNomGenre (String nomGenre);
}
