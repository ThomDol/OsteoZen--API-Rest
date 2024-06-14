package org.gestion_patient.mapper;

import org.gestion_patient.entity.Lieu;
import org.gestion_patient.entityDto.LieuDto;

public class LieuMapper {
    public static LieuDto mapToLieuDto(Lieu lieu){
        return new LieuDto(
                lieu.getIdVille(),
                lieu.getNomVille(),
                lieu.getCodePostal()
        );
    }

    public static Lieu mapToLieu(LieuDto lieuDto){
        return new Lieu(
                lieuDto.getIdVille(),
                lieuDto.getNomville(),
                lieuDto.getCodePostal()
        );
    }
}
