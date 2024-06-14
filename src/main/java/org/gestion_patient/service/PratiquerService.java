package org.gestion_patient.service;

import org.gestion_patient.entityDto.PratiquerDto;
import org.gestion_patient.entityDto.SportDto;

import java.util.List;

public interface PratiquerService {
    PratiquerDto createByPatient(SportDto sportDto, int idPatient);
    List<PratiquerDto> getAllPratiquerByIdPatient(int idPatient );
}
