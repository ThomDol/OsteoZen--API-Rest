package org.gestion_patient.controller;

import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.MedecintraitantDto;
import org.gestion_patient.service.MedecinTraitantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/medecintraitant")
public class MedecinTraitantController {
    private MedecinTraitantService medecinTraitantService;

    @GetMapping("/{id}")
    public ResponseEntity<MedecintraitantDto> getMedecintraitantById(@PathVariable ("id") int id) throws Exception {
      MedecintraitantDto medecintraitant = medecinTraitantService.findMedecintraitantById(id);
      return new ResponseEntity<>(medecintraitant , HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedecintraitantDto>> getAllMedecinTRaitant (){
        List<MedecintraitantDto> medecintraitantDtosDtos = medecinTraitantService.getAll();
        return new ResponseEntity<>(medecintraitantDtosDtos,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <MedecintraitantDto> createMedecinTraitant (@RequestBody MedecintraitantDto medecintraitantDto) throws Exception {
       MedecintraitantDto medecintraitantToCeateDto = medecinTraitantService.createMedecintraitant(medecintraitantDto);
       return new ResponseEntity<>(medecintraitantToCeateDto, HttpStatus.CREATED);
      }

      @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedecinTraitant (@PathVariable ("id") int id) {
        medecinTraitantService.delete(id);
        return new ResponseEntity<>("Deletion complete",HttpStatus.OK);
    }

}
