package org.gestion_patient.controller;

import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.PatientDto;
import org.gestion_patient.repository.PraticienRepository;
import org.gestion_patient.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/patient")
public class PatientController {
    private PatientService patientService;
    private PraticienRepository praticienconnecteRepository;

    @PostMapping("/{idPraticienConnecte}")
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto,@PathVariable int idPraticienConnecte) throws Exception {
        PatientDto patientSaved = patientService.createPatient(patientDto,idPraticienConnecte);
        return new ResponseEntity<>(patientSaved, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PatientDto>> getAllPatient (){
        List<PatientDto> patients = patientService.getAllPatient();
        return new ResponseEntity<>(patients,HttpStatus.OK);
    }
    @GetMapping("/all/{idPraticienConnecte}")
    public ResponseEntity<List<PatientDto>>getAllPatientByIdPraticien (@PathVariable int idPraticienConnecte){
      List<PatientDto> patients = patientService.getAllPatientByPraticien(idPraticienConnecte);
      return new ResponseEntity<>(patients,HttpStatus.OK);
    }

    @GetMapping("/{idPraticien}/{id}")
    public ResponseEntity<PatientDto> getPatientById (@PathVariable int id,@PathVariable int idPraticien) throws Exception {
        PatientDto patientDto=patientService.getByIdAndIdPraticien(id,idPraticien);
        return new ResponseEntity<>(patientDto,HttpStatus.OK);
    }
    @DeleteMapping("/{idPraticien}/{id}")
    public ResponseEntity<String> deletePatient (@PathVariable int id ,@PathVariable int idPraticien) throws Exception {
        patientService.deletePatientByPraticien(id,idPraticien);
        return  new ResponseEntity<>("patient deleted with success" , HttpStatus.OK);

    }

    @PutMapping("{idPraticien}/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable int idPraticien,@PathVariable int id,@RequestBody PatientDto patientDto) throws Exception {
       PatientDto patientDtoUpdated = patientService.updatePatient(id,patientDto,idPraticien);
       return new ResponseEntity<>(patientDtoUpdated , HttpStatus.OK);
    }
}
