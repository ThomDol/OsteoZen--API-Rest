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
@Table(name = "type_patient")
public class TypePatient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_patient", nullable = false)
    private int idTypePatient;

    @Column(name = "nom_type_patient", nullable = false, length = 10)
    private String nomTypePatient;

}