package org.gestion_patient.controller;

import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.PatientDto;
import org.gestion_patient.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/patient")
public class PatientController {
    private PatientService patientService;


    @PostMapping("/{idAppUser}")
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto,@PathVariable ("idAppUser") int idAppUser) throws Exception {
        PatientDto patientSaved = patientService.createPatient(patientDto,idAppUser);
        return new ResponseEntity<>(patientSaved, HttpStatus.CREATED);
    }


    @GetMapping("/all/{idAppUser}")
    public ResponseEntity<List<PatientDto>>getAllPatientByIdAppUser (@PathVariable ("idAppUser") int idAppUser){
      List<PatientDto> patients = patientService.getAllPatientByAppUser(idAppUser);
      return new ResponseEntity<>(patients,HttpStatus.OK);
    }

    @GetMapping("/{idAppUser}/{id}")
    public ResponseEntity<PatientDto> getPatientById (@PathVariable ("id") int id,@PathVariable ("idAppUser") int idAppUser) throws Exception {
        PatientDto patientDto=patientService.getByIdAndIdAppUser(id,idAppUser);
        return new ResponseEntity<>(patientDto,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getById (@PathVariable ("id") int id) throws Exception {
        PatientDto patientDto = patientService.getById(id);
        return new ResponseEntity<>(patientDto, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient (@PathVariable ("id") int id) throws Exception {
        patientService.delete(id);
        return  new ResponseEntity<>("patient deleted with success" , HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable ("id") int id,@RequestBody PatientDto patientDto) throws Exception {
       PatientDto patientDtoUpdated = patientService.updatePatient(id,patientDto);
       return new ResponseEntity<>(patientDtoUpdated , HttpStatus.OK);
    }


}
