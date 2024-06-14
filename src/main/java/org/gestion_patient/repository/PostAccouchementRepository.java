package org.gestion_patient.repository;

import org.gestion_patient.entity.PostAccouchement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostAccouchementRepository extends JpaRepository<PostAccouchement,Integer> {
    PostAccouchement findByAccouchementIdAccouchement (int id);
}