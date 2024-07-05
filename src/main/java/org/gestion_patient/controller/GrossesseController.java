package org.gestion_patient.controller;

import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.GrossesseDto;
import org.gestion_patient.service.GrossesseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/grossesse")
public class GrossesseController {
    private GrossesseService grossesseService;


    @PostMapping("/{idPatient}")
    public ResponseEntity<GrossesseDto> createGrossesse(@RequestBody GrossesseDto grossesseDto, @PathVariable ("idPatient") int idPatient ) {
        GrossesseDto createAccouchement = grossesseService.create(grossesseDto,idPatient);
        return new ResponseEntity<>(createAccouchement, HttpStatus.CREATED);
    }
    @GetMapping("/{idGrossesse}")
    public ResponseEntity<GrossesseDto> getGrossesseById(@PathVariable ("idGrossesse") int idGrossesse) {
        GrossesseDto grossesseDto = grossesseService.getById(idGrossesse);
        return new ResponseEntity<>(grossesseDto, HttpStatus.OK);
    }
    @GetMapping("all/{idPatient}")
    public ResponseEntity<List<GrossesseDto>> getAllGrossesseByPatientId(@PathVariable ("idPatient") int idPatient)  {
        List<GrossesseDto> grossesseDtoList = grossesseService.getAllByidPatient(idPatient);
        return new ResponseEntity<>(grossesseDtoList, HttpStatus.OK);
    }

    @PutMapping("/{idGrossesse}")
    public ResponseEntity<GrossesseDto> updateGrossesse (@PathVariable ("idGrossesse") int idGrossesse, @RequestBody GrossesseDto grossesseDto){
        GrossesseDto grossesseDtoUpdated = grossesseService.update(idGrossesse,grossesseDto);
        return new ResponseEntity<>(grossesseDtoUpdated,HttpStatus.OK);
    }

    @DeleteMapping("/{idGrossesse}")
    public ResponseEntity<String> deleteGrossesse (@PathVariable ("idGrossesse") int idGrossesse){
        grossesseService.deleteGrossesse(idGrossesse);
        return new ResponseEntity<>("deleted with success",HttpStatus.OK);
    }





}
