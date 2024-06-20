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
@RequestMapping("/accouchement")
public class AccouchementController {
    private AccouchementService accouchementService;


    @PostMapping("/{idPatient}")
    public ResponseEntity<AccouchementDto> createAccouchement(@RequestBody AccouchementDto accouchementDto,@PathVariable int idPatient ) {
        AccouchementDto createAccouchement = accouchementService.create(accouchementDto,idPatient);
        return new ResponseEntity<>(createAccouchement, HttpStatus.CREATED);
    }
    @GetMapping("/{idPatient}/{idAccouchement}")
    public ResponseEntity<AccouchementDto> getAccouchementById(@PathVariable int idAccouchement,@PathVariable int idPatient) {
        AccouchementDto accouchements = accouchementService.getByIdAccouchementAndIdPatient(idAccouchement,idPatient);
        return new ResponseEntity<>(accouchements, HttpStatus.OK);
    }
    @GetMapping("patient/{idPatient}")
    public ResponseEntity<List<AccouchementDto>> getAllAccouchementByPatientId(@PathVariable int idPatient)  {
        List<AccouchementDto> listAccouchement = accouchementService.getAllByIdPatient(idPatient);
        return new ResponseEntity<>(listAccouchement, HttpStatus.OK);
    }

    @PutMapping("/{idPatient}/{id}")
    public ResponseEntity<String> updateAccouchement (@PathVariable int id,@PathVariable int idPatient,@RequestBody AccouchementDto accouchementDto){
        accouchementService.update(id,idPatient,accouchementDto);
        return new ResponseEntity<>("Accouchement updated with success",HttpStatus.OK);
    }

    public ResponseEntity<String> delete (@PathVariable int id,@PathVariable int idPatient){
        accouchementService.deleteAccouchement(id, idPatient);
        return new ResponseEntity<>("Deletion successfull",HttpStatus.OK);
    }


}
