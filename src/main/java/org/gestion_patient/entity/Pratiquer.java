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
@Table(name = "pratiquer")
public class Pratiquer {
    @EmbeddedId
    private PratiquerId idPratiquer;

    @MapsId("idSport")
    @ManyToOne( optional = false)
    @JoinColumn(name = "id_Sport", nullable = false)
    private Sport sport;

    @MapsId("idPatient")
    @ManyToOne( optional = false)
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;

}