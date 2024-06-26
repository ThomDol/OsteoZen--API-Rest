package org.gestion_patient.utils;

import org.gestion_patient.crypto.Crypto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptSensitiveData {

    public static void main(String[] args) {

        // Exemple de données à crypter
        String emailToEncrypt = "admin@gmail.com";
        String passwordToEncrypt = "admin";
        String nomToEncrypt = "Dupont";
        String prenomToEncrypt = "Jean";
        String telToEncrypt = "0123456789";
        String numAdmeliToEncrypt = "123456789012";
        String numSiretToEncrypt = "98765432109876";

        try {
            // Cryptage de l'email
            String encryptedEmail = Crypto.cryptService(emailToEncrypt);
            System.out.println("Encrypted Email: " + encryptedEmail);

            // Hashage du mot de passe avec BCrypt
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(passwordToEncrypt);
            System.out.println("Hashed Password: " + hashedPassword);

            // Cryptage du nom
            String encryptedNom = Crypto.cryptService(nomToEncrypt);
            System.out.println("Encrypted Nom: " + encryptedNom);

            // Cryptage du prénom
            String encryptedPrenom = Crypto.cryptService(prenomToEncrypt);
            System.out.println("Encrypted Prenom: " + encryptedPrenom);

            // Cryptage du numéro de téléphone
            String encryptedTel = Crypto.cryptService(telToEncrypt);
            System.out.println("Encrypted Telephone: " + encryptedTel);

            // Cryptage du numéro d'admel
            String encryptedNumAdmeli = Crypto.cryptService(numAdmeliToEncrypt);
            System.out.println("Encrypted Num Admeli: " + encryptedNumAdmeli);

            // Cryptage du numéro de SIRET
            String encryptedNumSiret = Crypto.cryptService(numSiretToEncrypt);
            System.out.println("Encrypted Num Siret: " + encryptedNumSiret);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
