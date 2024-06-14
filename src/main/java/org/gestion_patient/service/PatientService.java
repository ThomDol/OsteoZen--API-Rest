package org.gestion_patient.service;


import org.gestion_patient.entityDto.PatientDto;
import java.util.List;


public interface PatientService {
    PatientDto createPatient(PatientDto patientDto, int idPraticienConnecte) throws Exception;

    List<PatientDto> getAllPatientByPraticien(int idPraticien);

    PatientDto getById(int id) throws Exception;

    void deletePatient(int id);

    PatientDto updatePatient(int id, PatientDto upadtedPatientDto) throws Exception;

    List<PatientDto> getAllPatient();


}
