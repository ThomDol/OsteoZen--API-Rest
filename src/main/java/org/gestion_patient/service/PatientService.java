package org.gestion_patient.service;


import org.gestion_patient.entityDto.PatientDto;
import java.util.List;


public interface PatientService {
    PatientDto createPatient(PatientDto patientDto, int idPraticienConnecte) throws Exception;

    List<PatientDto> getAllPatientByAppUser(int idPraticien);


    void delete(int id);

    PatientDto getById(int id) throws Exception;

    PatientDto updatePatient(int id, PatientDto upadtedPatientDto) throws Exception;

    PatientDto getByIdAndIdAppUser(int id,int idPraticien) throws Exception;



}
