package org.gestion_patient.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.LOCKED)
public class AccountBlockedException extends RuntimeException{
    public  AccountBlockedException (String message){
        super(message);
    }
}