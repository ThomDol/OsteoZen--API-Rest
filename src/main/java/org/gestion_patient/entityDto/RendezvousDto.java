package org.gestion_patient.entityDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
