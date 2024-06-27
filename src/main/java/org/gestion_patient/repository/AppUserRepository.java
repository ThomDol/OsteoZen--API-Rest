package org.gestion_patient.repository;

import org.gestion_patient.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Integer> {
        AppUser findByIdentiteEmail(String email);
    }