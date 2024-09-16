package org.gestion_patient.controller;

import lombok.AllArgsConstructor;
import org.gestion_patient.entity.Accouchement;
import org.gestion_patient.entityDto.AccouchementDto;
import org.gestion_patient.service.AccouchementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/accouchement")
public class AccouchementController {
    private AccouchementService accouchementService;


    @PostMapping("/{idPatient}")
    public ResponseEntity<AccouchementDto> createAccouchement(@RequestBody AccouchementDto accouchementDto,@PathVariable ("idPatient") int idPatient ) {
        AccouchementDto createAccouchement = accouchementService.create(accouchementDto,idPatient);
        return new ResponseEntity<>(createAccouchement, HttpStatus.CREATED);
    }
    @GetMapping("/{idAccouchement}")
    public ResponseEntity<AccouchementDto> getAccouchementById(@PathVariable ("idAccouchement") int idAccouchement) {
        AccouchementDto accouchements = accouchementService.getByIdAccouchement(idAccouchement);
        return new ResponseEntity<>(accouchements, HttpStatus.OK);
    }
    @GetMapping("/all/{idPatient}")
    public ResponseEntity<List<AccouchementDto>> getAllAccouchementByPatientId(@PathVariable ("idPatient") int idPatient)  {
        List<AccouchementDto> listAccouchement = accouchementService.getAllByIdPatient(idPatient);
        return new ResponseEntity<>(listAccouchement, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccouchementDto> updateAccouchement (@PathVariable ("id") int id,@RequestBody AccouchementDto accouchementDto){
        AccouchementDto accouchementUpdated = accouchementService.update(id,accouchementDto);
        return new ResponseEntity<>(accouchementUpdated,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable ("id") int id){
        accouchementService.deleteAccouchement(id);
        return new ResponseEntity<>("Deletion successfull",HttpStatus.OK);
    }


}
