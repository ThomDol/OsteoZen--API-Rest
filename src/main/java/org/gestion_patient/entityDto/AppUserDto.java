package org.gestion_patient.entityDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {

    private int idAppUser;
    private String password;
    private String nomRole;
    private String nomVille;
    private String codePostal;
    private String numAdeli;
    private String numSiret;
    private String nomAppUser;
    private String prenomAppUser;
    private String email;
    private String tel;


}
