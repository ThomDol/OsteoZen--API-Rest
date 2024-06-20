package org.gestion_patient.service;


import org.gestion_patient.entityDto.AntecedentClassiqueDto;


public interface AntecedentClassiqueService {
    AntecedentClassiqueDto create(AntecedentClassiqueDto antecedentAdulteEnfantDto, int idPatient) throws Exception;
    AntecedentClassiqueDto update(int id,int idPatient, AntecedentClassiqueDto antecedentAdulteEnfantDtoUpdated) throws Exception;
    AntecedentClassiqueDto getByIdAndIdPatient(int id,int idPatient) throws Exception;

}