package org.gestion_patient.service;


import org.gestion_patient.entityDto.PatientDto;
import java.util.List;


public interface PatientService {
    PatientDto createPatient(PatientDto patientDto, int idPraticienConnecte) throws Exception;

    List<PatientDto> getAllPatientByPraticien(int idPraticien);


    void deletePatientByPraticien(int id,int idPraticien);

    PatientDto updatePatient(int id, PatientDto upadtedPatientDto,int idPraticien) throws Exception;

    List<PatientDto> getAllPatient();
    PatientDto getByIdAndIdPraticien(int id,int idPraticien) throws Exception;


}
