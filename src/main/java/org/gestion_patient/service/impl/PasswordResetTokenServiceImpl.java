package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.AppUser;
import org.gestion_patient.entity.PasswordResetToken;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.repository.AppUserRepository;
import org.gestion_patient.repository.PasswordResetTokenRepository;
import org.gestion_patient.service.EmailService;
import org.gestion_patient.service.PasswordResetTokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    private AppUserRepository appUserRepository;
    private PasswordResetTokenRepository tokenRepository;
    private EmailService emailService;
    private PasswordEncoder passwordEncoder;


    @Override
    public void forgotPassword(String email) throws Exception {
        AppUser appUser = appUserRepository.findByIdentiteEmail(Crypto.cryptService(email));
        if (appUser == null) {
            throw new ResourceNotFoundException ("AppUser not Found");
        }
        //Efface token si déjà un
        PasswordResetToken passwordResetToken = tokenRepository.findByAppUser(appUser);
        if (passwordResetToken!=null){tokenRepository.delete(passwordResetToken);}

        //Generation nouveau Token
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setAppUser(appUser);
        resetToken.setExpiryDate(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)); // 24 hours expiration
        tokenRepository.save(resetToken);

        //Envoi du token par lien par email
        String resetUrl = "http://localhost:5173/reset-password?token=" + token;
        emailService.sendEmail(Crypto.decryptService(appUser.getIdentite().getEmail()), "Password Reset Request", "To reset your password, click the link below:\n" + resetUrl);
    }

    @Override
    public void resetPassword(String password,String token) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if (resetToken == null || resetToken.getExpiryDate().before(new Date())) {
           throw new ResourceNotFoundException("token not found");
        }

        AppUser appUser= resetToken.getAppUser();
        appUser.setPassword(passwordEncoder.encode(password));
        appUserRepository.save(appUser);

        tokenRepository.delete(resetToken);

    }
}
