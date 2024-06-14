package org.gestion_patient.entityDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class MedecintraitantDto {
    private int idMedecinTraitant;
    private String nomMedecinTraitant;
    private String prenomMedecinTraitant;
    private String ville;
}
