package org.gestion_patient.controller;

import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.ProfessionDto;
import org.gestion_patient.service.ProfessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/profession")
public class ProfessionController {
    private ProfessionService professionService;

    @GetMapping
    public ResponseEntity<List<ProfessionDto>> getAllProfession (){
        List<ProfessionDto> professionDtos = professionService.findAll();
        return new ResponseEntity<>(professionDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionDto> getProfessionById (@PathVariable int id){
        ProfessionDto professionDto = professionService.getById(id);
        return new ResponseEntity<>(professionDto,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfessionDto> createNewProfession (@RequestBody ProfessionDto professionDto){
        ProfessionDto professionDtoCreated = professionService.create(professionDto);
        return new ResponseEntity<>(professionDtoCreated,HttpStatus.CREATED);
    }
}
