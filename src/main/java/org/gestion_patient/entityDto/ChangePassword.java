package org.gestion_patient.entityDto;

import lombok.Data;

@Data
public class ChangePassword {
    private String oldPassword;
    private String newPassword;
}
