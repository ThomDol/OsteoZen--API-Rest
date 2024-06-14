package org.gestion_patient.entityDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gestion_patient.entity.Patient;

import java.time.LocalDate;


@Data
@AllArgsConstructor @NoArgsConstructor
public class PhysiqueDto {
    private int idPhysique;
    private String dateMesure;
    private Float poids;
    private Float taille;
    private Boolean droitier;
    private Boolean lunettes;
    private Boolean dentaire;
    private int idPatient;


}
