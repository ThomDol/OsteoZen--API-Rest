package org.gestion_patient.mapper;

import org.gestion_patient.entity.AppUser;
import org.gestion_patient.entity.PasswordResetToken;
import org.gestion_patient.entityDto.PasswordResetTokenDto;

public class PasswordResetTokenMapper {
    public static PasswordResetTokenDto mapToPasswordResetTokenDto(PasswordResetToken passwordResetToken){
        return new PasswordResetTokenDto(
                passwordResetToken.getId(),
                passwordResetToken.getToken(),
                passwordResetToken.getExpiryDate(),
                passwordResetToken.getAppUser().getIdAppUser()
        );
    }

    public static PasswordResetToken mapToPasswordResetToken (PasswordResetTokenDto passwordResetTokenDto, AppUser appUser){
        return  new PasswordResetToken(
                passwordResetTokenDto.getId(),
                passwordResetTokenDto.getToken(),
                passwordResetTokenDto.getExpiryDate(),
                appUser
        );
    }

}
