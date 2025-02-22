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
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/lieu")
public class LieuController {
    private LieuService lieuService;

    @PostMapping
    public ResponseEntity<LieuDto> createLieu(@RequestBody LieuDto lieuDto) {
       LieuDto lieu = lieuService.createLieu(lieuDto);
       return new ResponseEntity<>(lieu , HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<LieuDto>> getAllLieu(){
        List<LieuDto> lieux = lieuService.findAll();
        return new ResponseEntity<>(lieux,HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<LieuDto> getLieuById(@PathVariable ("id") int id) {
        LieuDto Lieu = lieuService.getLieuById(id);
        return new ResponseEntity<>(Lieu , HttpStatus.OK);
    }
    @DeleteMapping ("{id}")
    public ResponseEntity<String> deleteLieu(@PathVariable ("id") int id) {
        lieuService.deleteLieu(id);
        return new ResponseEntity<>("localisation deleted successfully", HttpStatus.OK);
    }

}
