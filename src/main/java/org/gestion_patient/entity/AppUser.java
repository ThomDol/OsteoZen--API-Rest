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
@Table(name = "appuser")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_appuser", nullable = false)
    private int idAppUser;

    @Column(name = "password", nullable = false, length = 80)
    private String password;

    @ManyToOne( optional = false)
    @JoinColumn(name = "id_role", nullable = false)
    private Role role;

    @ManyToOne( optional = false)
    @JoinColumn(name = "id_ville", nullable = false)
    private Lieu ville;

    @ManyToOne( optional = false)
    @JoinColumn(name = "id_infos_professionnelles", nullable = false)
    private Infosprofessionnelles infosProfessionnelles;

    @OneToOne( optional = false)
    @JoinColumn(name = "id_identite", nullable = false)
    private Personne identite;






}