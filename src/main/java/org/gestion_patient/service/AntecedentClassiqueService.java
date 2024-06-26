package org.gestion_patient.service;


import org.gestion_patient.entityDto.AntecedentClassiqueDto;


public interface AntecedentClassiqueService {
    AntecedentClassiqueDto create(AntecedentClassiqueDto antecedentAdulteEnfantDto, int idPatient) throws Exception;
    AntecedentClassiqueDto update(int id, AntecedentClassiqueDto antecedentAdulteEnfantDtoUpdated) throws Exception;
    AntecedentClassiqueDto getByIdPatient(int idPatient) throws Exception;
    void deleteAntecedentClassique(int id);

}