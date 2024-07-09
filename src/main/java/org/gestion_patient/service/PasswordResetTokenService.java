package org.gestion_patient.service;

public interface PasswordResetTokenService {
    void forgotPassword(String email) throws Exception;
    void resetPassword(String password,String token);
}
