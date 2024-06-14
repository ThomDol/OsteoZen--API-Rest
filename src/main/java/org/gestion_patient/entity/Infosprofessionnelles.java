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
@Table(name = "infosprofessionnelles")
public class Infosprofessionnelles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_infos_professionnelles", nullable = false)
    private int idInfosProfessionnelles;

    @Column(name = "num_ADELI", nullable = false, length = 100)
    private String numAdeli;

    @Column(name = "num_SIRET", nullable = false, length = 100)
    private String numSiret;

}