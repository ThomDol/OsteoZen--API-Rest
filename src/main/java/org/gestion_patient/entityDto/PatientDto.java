package org.gestion_patient.entityDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gestion_patient.entity.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {

    private int idPatient;
    private String dateNaissance;
    private String nomVille;
    private String codePostal;
    private String nomGenre;
    private String nomProfession;
    private String nomTypePatient;
    private String nomMedecinTraitant;
    private String prenomMedecinTraitant;
    private String villeMedecinTraitant;
    private String nomPatient ;
    private String prenomPatient ;
    private String email;
    private String tel;
    private int idPraticien;

}
