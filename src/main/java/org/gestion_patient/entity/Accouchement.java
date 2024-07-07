package org.gestion_patient.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accouchement")
public class Accouchement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_accouchement", nullable = false)
    private int idAccouchement;

    @Column(name = "date_accouchement", nullable = false)
    private String dateAccouchement;

    @Column(name = "duree_travail", nullable = true)
    private Integer dureeTravail;

    @Column(name = "difficulte_travail", nullable = true, length = 100)
    private String difficulteTravail;

    @Column(name = "accouchement_provoque", nullable = true)
    private Boolean accouchementProvoque;

    @Column(name = "cesarienne", nullable = true)
    private Boolean cesarienne;

    @Column(name = "peridurale", nullable = true)
    private Boolean peridurale;

    @Column(name = "extraction_instrumentale", nullable = true)
    private Boolean extractionInstrumentale;

    @Column(name = "ocytocine", nullable = true)
    private Boolean ocytocine;

    @Column(name = "circulaire_du_cordon_ombilical", nullable = true)
    private Boolean circulaireDuCordonOmbilical;

    @Column(name = "aide_manuelle_poussee", nullable = true)
    private Boolean aideManuellePoussee;

    @Column(name = "complication", nullable = true, length = 100)
    private String complication;

    @Column(name = "episiotomie", nullable = true)
    private Boolean episiotomie;

    @Column(name = "dechirure", nullable = true)
    private Boolean dechirure;

    @Column(name = "reeducation_perinee", nullable = true)
    private Boolean reeducationPerinee;

    @Column(name = "presentation_a_accouchement", nullable = true, length = 100)
    private String presentationAAccouchement;

    @Column(name = "age_date_accouchement", nullable = true)
    private Integer ageDateAccouchement;

    @OneToOne
    @JoinColumn(name="id_grossesse_postpartum",nullable = true)
    private PostAccouchement postAccouchement;

    @ManyToOne( optional = false)
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;

}