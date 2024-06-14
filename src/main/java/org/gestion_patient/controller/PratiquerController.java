package org.gestion_patient.controller;


import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.PratiquerDto;
import org.gestion_patient.entityDto.SportDto;
import org.gestion_patient.service.PratiquerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pratiquer")
public class PratiquerController {
    PratiquerService pratiquerService;

    @PostMapping("/{idPatient}")
    public ResponseEntity<PratiquerDto> createNewPratiquer(@RequestBody SportDto sportDto,@PathVariable int idPatient){
        PratiquerDto pratiquerDtoSaved = pratiquerService.createByPatient(sportDto,idPatient);
        return new ResponseEntity<>(pratiquerDtoSaved, HttpStatus.CREATED);
    }

    @GetMapping("/{idPatient}")
    public ResponseEntity<List<PratiquerDto>> getAllSportByPatient(@PathVariable int idPatient){
        List<PratiquerDto> pratiquerDtoList =  pratiquerService.getAllPratiquerByIdPatient(idPatient);
        return new ResponseEntity<>(pratiquerDtoList,HttpStatus.OK);
    }


}
