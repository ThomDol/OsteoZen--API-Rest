package org.gestion_patient.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Grossesse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grossesse", nullable = false)
    private int idGrossesse;

    @Column(name = "date_creation", nullable = false, length = 10)
    private String dateCreation;

    @Column(name = "date_update", nullable = true, length = 10)
    private String dateUpdate;

    @Column(name = "maternite", nullable = true, length = 100)
    private String maternite;

    @Column(name = "grossesse_multiple", nullable = true)
    private Boolean grossesseMultiple;

    @Column(name = "douleurs_pendant_grossesse", nullable = true, length = 100)
    private String douleursPendantGrossesse;

    @Column(name = "etat_psycho_emotionnel", nullable = true, length = 100)
    private String etatPsychoEmotionnel;

    @Column(name = "traitement_lie_grossesse", nullable = true, length = 200)
    private String traitementLieGrossesse;

    @Column(name = "mouvements_bebe", nullable = true, length = 200)
    private String mouvementsBebe;

    @Column(name = "cesarienne_prevue", nullable = true)
    private Boolean cesariennePrevue;

    @Column(name = "projet_peridurale", nullable = true)
    private Boolean projetPeridurale;

    @Column(name = "projet_allaitement", nullable = true)
    private Boolean projetAllaitement;

    @Column(name = "nausees", nullable = true)
    private Boolean nausees;

    @Column(name = "constipation", nullable = true)
    private Boolean constipation;

    @Column(name = "diarrhees", nullable = true)
    private Boolean diarrhees;

    @Column(name = "aigreurs_estomac", nullable = true)
    private Boolean aigreursEstomac;

    @Column(name = "oedemes_membres_inferieurs", nullable = true)
    private Boolean oedemesMembresInferieurs;

    @Column(name = "pesanteur_pelvienne", nullable = true)
    private Boolean pesanteurPelvienne;

    @Column(name = "incontinence", nullable = true)
    private Boolean incontinence;

    @Column(name = "tension_mammaire", nullable = true)
    private Boolean tensionMammaire;

    @Column(name = "mastose", nullable = true)
    private Boolean mastose;

    @ManyToOne( optional = false)
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;
}