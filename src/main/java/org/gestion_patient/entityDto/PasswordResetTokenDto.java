package org.gestion_patient.entityDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.gestion_patient.entity.AppUser;

import java.util.Date;

@AllArgsConstructor
@Data
public class PasswordResetTokenDto {
    private int id;
    private String token;
    private Date expiryDate;
    private int idAppUser;
}
