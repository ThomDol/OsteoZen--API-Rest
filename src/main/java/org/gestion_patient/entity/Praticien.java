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
@Table(name = "praticien")
public class Praticien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_praticien", nullable = false)
    private int idPraticien;

    @Column(name = "password", nullable = false, length = 80)
    private String password;

    @Column(name = "username",unique = true, nullable = false, length = 80)
    private String username;


    @ManyToOne( optional = false)
    @JoinColumn(name = "id_role", nullable = false)
    private Role role;

    @ManyToOne( optional = false)
    @JoinColumn(name = "id_ville", nullable = false)
    private Lieu ville;

    @ManyToOne( optional = false)
    @JoinColumn(name = "id_infos_professionnelles", nullable = false)
    private Infosprofessionnelles infosProfessionnelles;

    @ManyToOne( optional = false)
    @JoinColumn(name = "id_identite", nullable = false)
    private Personne identite;






}