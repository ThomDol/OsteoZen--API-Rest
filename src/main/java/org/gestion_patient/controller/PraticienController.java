package org.gestion_patient.controller;

import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.PraticienDto;
import org.gestion_patient.service.PraticienService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/api")
public class PraticienController {
    private PraticienService praticienService;

    @GetMapping(path="/profile")
    public ResponseEntity<PraticienDto> profile(Principal principal) throws Exception {
        PraticienDto praticienDto =  praticienService.loadByEmail(principal.getName());
        return new ResponseEntity<>(praticienDto,HttpStatus.CREATED);
    }

    @PostMapping("/praticien")
    public ResponseEntity<PraticienDto> createNewPraticien(@RequestBody PraticienDto praticienconnecteDto) throws Exception {
        PraticienDto praticienDtoSaved = praticienService.create(praticienconnecteDto);
        return new ResponseEntity<>(praticienDtoSaved, HttpStatus.CREATED);
    }

    @GetMapping("/praticien/all")
    public ResponseEntity<List<PraticienDto>> getAllPraticien(){
        List<PraticienDto> praticiens = praticienService.findAll();
        return new ResponseEntity<>(praticiens,HttpStatus.OK);
    }

    @GetMapping("/praticien/{id}")
    public ResponseEntity<PraticienDto> getPraticienById(@PathVariable int id) throws Exception {
        PraticienDto praticienconnecteDto = praticienService.findById(id);
        return new ResponseEntity<>(praticienconnecteDto,HttpStatus.OK);
    }

    @PutMapping("/praticien/{id}")
    public ResponseEntity<PraticienDto> updatePraticien(@PathVariable int id, @RequestBody PraticienDto praticienconnecteDto) throws Exception {
        PraticienDto updatedPraticien = praticienService.update(id,praticienconnecteDto);
        return new ResponseEntity<>(updatedPraticien,HttpStatus.OK);
    }



}