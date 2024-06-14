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
@Table(name = "sport")
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sport", nullable = false)
    private int idSport;

    @Column(name = "nom_sport", nullable = false, length = 50)
    private String nomSport;

    @ManyToMany(mappedBy = "sportList")
    private List<Patient> patientList = new ArrayList<>();

}