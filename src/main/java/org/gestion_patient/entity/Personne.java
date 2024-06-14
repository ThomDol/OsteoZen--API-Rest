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
@Table(name = "personne")
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_identite", nullable = false)
    private int idIdentite;

    @Column(name = "nom", nullable = false, length = 80)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 80)
    private String prenom;

    @Column(name = "email",nullable=true, length = 150)
    private String email;

    @Column(name = "tel", nullable = true, length = 80)
    private String tel;



}