package org.gestion_patient.controller;


import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.PhysiqueDto;
import org.gestion_patient.service.PhysiqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/api/physique")
public class PhysiqueController {

    private PhysiqueService physiqueService;

    @PostMapping("/{idPatient}")
    public ResponseEntity<PhysiqueDto> createPhysique(@RequestBody PhysiqueDto physiqueDto, @PathVariable int idPatient ) {
        PhysiqueDto physiqueCreated = physiqueService.create(physiqueDto,idPatient);
        return new ResponseEntity<>(physiqueCreated, HttpStatus.CREATED);
    }

    @GetMapping("{idPhysique}")
    public ResponseEntity<PhysiqueDto> getPhysiqueById(@PathVariable int idPhysique) {
        PhysiqueDto physiqueDto = physiqueService.getByIdPhysyque(idPhysique);
        return new ResponseEntity<>(physiqueDto, HttpStatus.OK);
    }
    @GetMapping("all/{idPatient}")
    public ResponseEntity<List<PhysiqueDto>> getAllPhysiqueByPatientId(@PathVariable int idPatient)  {
        List<PhysiqueDto> listPhysique = physiqueService.getAllByIdPatient(idPatient);
        return new ResponseEntity<>(listPhysique, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhysiqueDto> updatePhysique (@PathVariable int id,@RequestBody PhysiqueDto physiqueDto){
        PhysiqueDto physiqueUpdated = physiqueService.update(id,physiqueDto);
        return new ResponseEntity<>(physiqueUpdated,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable int id){
        physiqueService.deletePhysique(id);
        return new ResponseEntity<>("Deletion successfull",HttpStatus.OK);
    }


}

