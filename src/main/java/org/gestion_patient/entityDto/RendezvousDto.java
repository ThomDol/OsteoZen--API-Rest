package org.gestion_patient.entityDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entity.Rendezvous;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class RendezvousDto {

    private int idRendezVous;
    private String dateRendeVous;
    private String syntheseRendezVous;
    private String schemaRendezVous;
    private String nomFacture;
    private String nomCourrier;
    private int idPatient;

}
