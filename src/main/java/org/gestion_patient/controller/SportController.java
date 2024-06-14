package org.gestion_patient.controller;

import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.SportDto;
import org.gestion_patient.service.SportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sport")
public class SportController {
    private SportService sportService;

    @PostMapping
    public ResponseEntity<SportDto> createNewSport (@RequestBody  SportDto sportDto){
        SportDto savedSportDto = sportService.create(sportDto);
        return new ResponseEntity<>(savedSportDto,HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<SportDto>> getAllSports(){
        List<SportDto> sports = sportService.findAll();
        return new ResponseEntity<>(sports, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SportDto> getSportById (@PathVariable int id){
        SportDto sportDto = sportService.findById(id);
        return new ResponseEntity<>(sportDto,HttpStatus.CREATED);
    }


}




