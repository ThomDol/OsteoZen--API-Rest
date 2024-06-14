package org.gestion_patient.mapper;

import org.gestion_patient.entity.Sport;
import org.gestion_patient.entityDto.SportDto;

public class SportMapper {
    public static SportDto mapToSportDto(Sport sport){
        return new SportDto(
                sport.getIdSport(),
                sport.getNomSport()
        );
    }
    public static Sport mapToSport(SportDto sportDto){
        Sport sport = new Sport();
        sport.setIdSport(sportDto.getIdSport());
        sport.setNomSport(sportDto.getNomSport());
        return sport;

    }
}
