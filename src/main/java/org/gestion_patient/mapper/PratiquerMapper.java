package org.gestion_patient.mapper;

import org.gestion_patient.entity.*;
import org.gestion_patient.entityDto.PratiquerDto;

public class PratiquerMapper {
    public static PratiquerDto mapToPratiquerDto (Pratiquer pratiquer){
        return new PratiquerDto(
                pratiquer.getSport().getIdSport(),
                pratiquer.getSport().getNomSport(),
                pratiquer.getPatient().getIdPatient());
    }

    public static Pratiquer mapToPratiquer(PratiquerDto pratiquerDto, Sport sport , Patient patient){
                PratiquerId pratiquerId = new PratiquerId();
        pratiquerId.setIdSport(pratiquerDto.getIdSport());
        pratiquerId.setIdPatient(pratiquerDto.getIdPatient());
        Pratiquer pratiquer = new Pratiquer(pratiquerId,sport,patient);
        return pratiquer;
    }
}
