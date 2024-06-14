package org.gestion_patient.service;

import org.gestion_patient.entityDto.AccouchementDto;

import java.util.List;

public interface AccouchementService {
    AccouchementDto create(AccouchementDto accouchementDto , int idPatient );
    AccouchementDto update(int id,AccouchementDto accouchementDtoDtoUpdated);
   List<AccouchementDto> getAllByIdPatient(int idPatient );
    AccouchementDto getByIdAccouchement(int id);
    void deleteAccouchement (int id);
}
