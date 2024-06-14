package org.gestion_patient.exception;

public class RessourceAlreadyexistsException extends RuntimeException {
    public RessourceAlreadyexistsException(String message) {
        super(message);
    }
}
