package org.gestion_patient.entityDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GrossesseDto {

    private int idGrossesse;
    private String dateCreation;
    private String dateUpdate;
    private String maternite;
    private Boolean grossesseMultiple;
    private String douleursPendantGrossesse;
    private String etatPsychoEmotionnel;
    private String traitementLieGrossesse;
    private String mouvementsBebe;
    private Boolean cesariennePrevue;
    private Boolean projetPeridurale;
    private Boolean projetAllaitement;
    private Boolean nausees;
    private Boolean constipation;
    private Boolean diarrhees;
    private Boolean aigreursEstomac;
    private Boolean oedemesMembresInferieurs;
    private Boolean pesanteurPelvienne;
    private Boolean incontinence;
    private Boolean tensionMammaire;
    private Boolean mastose;
    private int idPatient;

}