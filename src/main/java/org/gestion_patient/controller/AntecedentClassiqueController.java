package org.gestion_patient.controller;


import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.AntecedentClassique;
import org.gestion_patient.service.AntecedentClassiqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/antecedent")
public class AntecedentClassiqueController {
    private AntecedentClassiqueService antecedentAdulteEnfantServiceService;

    @PostMapping("/{idPatient}")
    public ResponseEntity<AntecedentClassique> createAntecedent (@RequestBody AntecedentClassique antecedentAdulteEnfantDto, @PathVariable int idPatient) throws Exception {
        AntecedentClassique antecedentAdulteEnfantDtoSaved = antecedentAdulteEnfantServiceService.create(antecedentAdulteEnfantDto,idPatient);
        return new ResponseEntity<>(antecedentAdulteEnfantDtoSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{idAntecedent}")
    public ResponseEntity<AntecedentClassique> updateAntecedent (@PathVariable int idAntecedent, @RequestBody AntecedentClassique antecedentAdulteEnfantDto ) throws Exception {
        AntecedentClassique antecedentAdulteEnfantDtoUpdated = antecedentAdulteEnfantServiceService.update(idAntecedent,antecedentAdulteEnfantDto);
        return new ResponseEntity<>(antecedentAdulteEnfantDtoUpdated,HttpStatus.OK);
    }

    @GetMapping("/{idPatient}")
    public ResponseEntity<AntecedentClassique> getAntecedentssanteByIdPatient (@PathVariable int idPatient) throws Exception {
        AntecedentClassique antecedentAdulteEnfantDto = antecedentAdulteEnfantServiceService.getByidPatient(idPatient);
        return new ResponseEntity<>(antecedentAdulteEnfantDto,HttpStatus.OK);
    }

}