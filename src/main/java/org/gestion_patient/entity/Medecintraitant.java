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
@Table(name = "medecintraitant")
public class Medecintraitant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medecin_traitant", nullable = false)
    private int idMedecinTraitant;

    @OneToOne( optional = false)
    @JoinColumn(name = "id_identite", nullable = false)
    private Personne identiteDoc;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_ville", nullable = false)
    private Lieu lieu;

}