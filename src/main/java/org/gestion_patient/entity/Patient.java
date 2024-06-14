package org.gestion_patient.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patient", nullable = false)
    private int idPatient;

    @Column(name = "date_naissance", nullable = false)
    private String dateNaissance;

    @ManyToOne
    @JoinColumn(name = "id_ville", nullable = true)
    private Lieu ville;

    @ManyToOne( optional = false)
    @JoinColumn(name = "id_genre", nullable = false)
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "id_profession", nullable = true)
    private Profession profession;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_type_patient", nullable = false)
    private TypePatient typePatient;

    @ManyToOne
    @JoinColumn(name = "id_medecin_traitant", nullable = true)
    private Medecintraitant medecinTraitant;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_identite", nullable = false)
    private Personne identite;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_praticien", nullable = false)
    private Praticien praticien;



    @ManyToMany
    @JoinTable(
            name="pratiquer",
            joinColumns = @JoinColumn(name = "id_patient"),
            inverseJoinColumns = @JoinColumn(name = "id_sport")
    )
    private List<Sport> sportList = new ArrayList<>();


}