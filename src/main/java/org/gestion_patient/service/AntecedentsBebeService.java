package org.gestion_patient.service;


import org.gestion_patient.entityDto.AntecedentsBebeDto;

public interface AntecedentsBebeService {
    AntecedentsBebeDto create(AntecedentsBebeDto antecedentBebeDto, int idPatient) throws Exception;
    AntecedentsBebeDto update(int id,int idPatient, AntecedentsBebeDto antecedentBebeDtoUpdated) throws Exception;
    AntecedentsBebeDto getByIdAndIdPatient(int id,int idPatient) throws Exception;
}