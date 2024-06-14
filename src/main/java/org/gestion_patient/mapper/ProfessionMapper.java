package org.gestion_patient.mapper;

import org.gestion_patient.entity.Profession;
import org.gestion_patient.entityDto.ProfessionDto;

public class ProfessionMapper {
    public static ProfessionDto mapToProfessionDto (Profession profession){
        return new ProfessionDto(
                profession.getIdProfession(),
                profession.getLibelleProfession()
        );
    }
    public static Profession mapToProfession(ProfessionDto professionDto){
        return new Profession(
                professionDto.getIdProfession(),
                professionDto.getLibelleProfession()
        );
    }
}
