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
@Table(name = "lieu")
public class Lieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ville", nullable = false)
    private int idVille;

    @Column(name = "ville", nullable = false, length = 100)
    private String nomVille;

    @Column(name = "code_postal", nullable = false, length = 5)
    private String codePostal;



}