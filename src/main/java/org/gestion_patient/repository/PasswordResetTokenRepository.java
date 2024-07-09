package org.gestion_patient.repository;

import org.gestion_patient.entity.AppUser;
import org.gestion_patient.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
    PasswordResetToken findByToken (String token);
    PasswordResetToken findByAppUser(AppUser appUser);
}