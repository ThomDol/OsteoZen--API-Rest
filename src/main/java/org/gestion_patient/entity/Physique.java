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
@Table(name = "physique")
public class Physique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPhysique", nullable = false)
    private int idPhysique;

    @Column(name = "date_mesure", nullable = false)
    private String dateMesure;

    @Column(name = "poids", nullable = true)
    private Float poids;

    @Column(name = "taille", nullable = true)
    private Float taille;

    @Column(name = "droitier", nullable = true)
    private Boolean droitier;

    @Column(name = "lunettes", nullable = true)
    private Boolean lunettes;

    @Column(name = "dentaire", nullable = true)
    private Boolean dentaire;

    @ManyToOne( optional = false)
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;


}