package org.gestion_patient.controller;

import lombok.AllArgsConstructor;
import org.gestion_patient.entity.Lieu;
import org.gestion_patient.entityDto.LieuDto;
import org.gestion_patient.service.LieuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/lieu")
public class LieuController {
    private LieuService lieuService;

    @PostMapping
    public ResponseEntity<LieuDto> createLieu(@RequestBody LieuDto lieuDto) {
       LieuDto lieu = lieuService.createLieu(lieuDto);
       return new ResponseEntity<>(lieu , HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<LieuDto>> getAllLieu(){
        List<LieuDto> lieux = lieuService.findAll();
        return new ResponseEntity<>(lieux,HttpStatus.OK);
    }

    @PutMapping (("{id}"))
    public ResponseEntity<String> updateLieu(@RequestBody LieuDto lieuDto ,@PathVariable int id ) {
        LieuDto lieuDto1 = lieuService.updateLieu(id, lieuDto);
        return new ResponseEntity<>("localisation updated successfully", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<LieuDto> getLieuxById(@PathVariable int id) {
        LieuDto Lieux = lieuService.getLieuById(id);
        return new ResponseEntity<>(Lieux , HttpStatus.OK);
    }
    @DeleteMapping ("{id}")
    public ResponseEntity<String> deleteLieux(@PathVariable int id) {
        lieuService.deleteLieu(id);
        return new ResponseEntity<>("localisation deleted successfully", HttpStatus.OK);
    }

}
