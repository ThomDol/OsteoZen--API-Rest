package org.gestion_patient.utils;

import org.gestion_patient.crypto.Crypto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DecryptSensitiveData {

    public static void main(String[] args) {

        // Exemple de données à décrypter
        String hashedPasswordFromDatabase = "$2a$10$PDhZVpsZNmPNZvhwK013Juz.ulIzTI1bR0ZmTy0vP6MiWsyre3TMC";
        String encryptedEmailFromDatabase = "zyK05lt3svyfCs+ey0IZiw==";
        String encryptedNomFromDatabase = "YDvSI2nw7DHuD3OaWVvTkQ==";
        String encryptedPrenomFromDatabase = "XvUr+GyCvc670p3aD7LYCg==";
        String encryptedTelFromDatabase = "ymOUHXajM1BVWQRQyV47vQ==";
        String encryptedNumAdmeliFromDatabase = "oWE3KhMpjqzHYZoorftNbg==";
        String encryptedNumSiretFromDatabase = "gA3iLqoDxlanBjmiajDGyQ==";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Vérification du mot de passe
        String passwordToCheck = "admin";
        boolean passwordMatches = passwordEncoder.matches(passwordToCheck, hashedPasswordFromDatabase);
        System.out.println("Password matches: " + passwordMatches);

        try {
            // Décryptage de l'email
            String decryptedEmail = Crypto.decryptService(encryptedEmailFromDatabase);
            System.out.println("Decrypted Email: " + decryptedEmail);

            // Décryptage du nom
            String decryptedNom = Crypto.decryptService(encryptedNomFromDatabase);
            System.out.println("Decrypted Nom: " + decryptedNom);

            // Décryptage du prénom
            String decryptedPrenom = Crypto.decryptService(encryptedPrenomFromDatabase);
            System.out.println("Decrypted Prenom: " + decryptedPrenom);

            // Décryptage du numéro de téléphone
            String decryptedTel = Crypto.decryptService(encryptedTelFromDatabase);
            System.out.println("Decrypted Telephone: " + decryptedTel);

            // Décryptage du numéro d'admel
            String decryptedNumAdmeli = Crypto.decryptService(encryptedNumAdmeliFromDatabase);
            System.out.println("Decrypted Num Admeli: " + decryptedNumAdmeli);

            // Décryptage du numéro de SIRET
            String decryptedNumSiret = Crypto.decryptService(encryptedNumSiretFromDatabase);
            System.out.println("Decrypted Num Siret: " + decryptedNumSiret);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
