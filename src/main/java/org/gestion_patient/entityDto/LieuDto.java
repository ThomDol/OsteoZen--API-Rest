package org.gestion_patient.entityDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class LieuDto {
    private int idVille;
    private String nomville;
    private String codePostal;

}
