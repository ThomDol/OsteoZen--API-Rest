package org.gestion_patient.service;


import org.gestion_patient.entityDto.AntecedentClassique;


public interface AntecedentClassiqueService {
    AntecedentClassique create(AntecedentClassique antecedentAdulteEnfantDto, int idPatient) throws Exception;
    AntecedentClassique update(int id, AntecedentClassique antecedentAdulteEnfantDtoUpdated) throws Exception;
    AntecedentClassique getByidPatient(int id) throws Exception;

}