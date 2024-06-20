package org.gestion_patient.entityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class AntecedentClassiqueDto {

    private int idAntecedentAdulteEnfant;
    private String dateCreation;
    private String dateUpdate;
    private Integer grossesse;
    private Boolean fumeur;
    private String allergie;
    private String traitement;
    private String antTraumatique;
    private String antChirurgicaux;
    private String antFamilliaux;
    private String antOrl;
    private String antVisceral;
    private String antCardioPulmonaire;
    private String antUroGynecaux;
    private String antPsy;
    private String antNotesDiverses;
    private int idPatient;

}