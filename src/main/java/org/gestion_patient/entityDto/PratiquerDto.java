package org.gestion_patient.entityDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entity.PratiquerId;
import org.gestion_patient.entity.Sport;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class PratiquerDto {

    private int idSport;
    private String nomSport;
    private int idPatient;
}
