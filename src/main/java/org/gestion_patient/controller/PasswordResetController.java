package org.gestion_patient.controller;

import lombok.AllArgsConstructor;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.AppUser;
import org.gestion_patient.entity.PasswordResetToken;
import org.gestion_patient.repository.AppUserRepository;
import org.gestion_patient.repository.PasswordResetTokenRepository;
import org.gestion_patient.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/password")
public class PasswordResetController {

    private AppUserRepository appUserRepository;
    private PasswordResetTokenRepository tokenRepository;
    private EmailService emailService;

    private PasswordEncoder passwordEncoder;

    @PostMapping("/forgot")
    public ResponseEntity<String> processForgotPassword(@RequestParam("email") String email) throws Exception {
        AppUser appUser = appUserRepository.findByIdentiteEmail(Crypto.cryptService(email));
        if (appUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email not found");
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

        return ResponseEntity.ok("Password reset link sent to your email");
    }


    @PostMapping("/reset")
    public ResponseEntity<String> processResetPassword(@RequestParam("token") String token, @RequestParam("password") String password) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if (resetToken == null || resetToken.getExpiryDate().before(new Date())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token");
        }

        AppUser appUser= resetToken.getAppUser();
        appUser.setPassword(passwordEncoder.encode(password));
        appUserRepository.save(appUser);

        tokenRepository.delete(resetToken);

        return ResponseEntity.ok("Password successfully reset");
    }

}
