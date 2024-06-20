package org.gestion_patient.service;

import org.gestion_patient.entityDto.AccouchementDto;

import java.util.List;

public interface AccouchementService {
    AccouchementDto create(AccouchementDto accouchementDto , int idPatient );
    AccouchementDto update(int id,int idPatient,AccouchementDto accouchementDtoDtoUpdated);
   List<AccouchementDto> getAllByIdPatient(int idPatient );
    AccouchementDto getByIdAccouchementAndIdPatient(int id,int idPatient);
    void deleteAccouchement (int id,int idPatient);
}
