package org.gestion_patient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (value = HttpStatus.NOT_FOUND)
public class ForbiddenAccessException extends RuntimeException{
    public  ForbiddenAccessException (String message){
        super(message);
    }
}
