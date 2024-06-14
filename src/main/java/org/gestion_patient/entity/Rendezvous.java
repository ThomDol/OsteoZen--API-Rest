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
@Table(name = "rendezvous")
public class Rendezvous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rendez_vous", nullable = false)
    private int idRendezVous;

    @Column(name = "date_rendez_vous", nullable = false)
    private String dateRendeVous;

    @Lob
    @Column(name = "synthese_rendez_vous",columnDefinition = "TEXT", nullable = true)
    private String syntheseRendezVous;

    @Lob
    @Column(name = "schema_rendez_vous", nullable = true)
    private String schemaRendezVous;

    @Lob
    @Column(name = "nom_facture", nullable = true)
    private String nomFacture;

    @Lob
    @Column(name = "nom_courrier", nullable = true)
    private String nomCourrier;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_patient", nullable = false)
    private  Patient patient;





}