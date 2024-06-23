package org.gestion_patient.controller;


import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.AntecedentClassiqueDto;
import org.gestion_patient.service.AntecedentClassiqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/antecedent")
public class AntecedentClassiqueController {
    private AntecedentClassiqueService antecedentClassiqueService;

    @PostMapping("/{idPatient}")
    public ResponseEntity<AntecedentClassiqueDto> createAntecedent (@RequestBody AntecedentClassiqueDto antecedentAdulteEnfantDto, @PathVariable int idPatient) throws Exception {
        AntecedentClassiqueDto antecedentAdulteEnfantDtoSaved = antecedentClassiqueService.create(antecedentAdulteEnfantDto,idPatient);
        return new ResponseEntity<>(antecedentAdulteEnfantDtoSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{idAntecedent}")
    public ResponseEntity<AntecedentClassiqueDto> updateAntecedent (@PathVariable int idAntecedent, @RequestBody AntecedentClassiqueDto antecedentAdulteEnfantDto ) throws Exception {
        AntecedentClassiqueDto antecedentAdulteEnfantDtoUpdated = antecedentClassiqueService.update(idAntecedent,antecedentAdulteEnfantDto);
        return new ResponseEntity<>(antecedentAdulteEnfantDtoUpdated,HttpStatus.OK);
    }

    @GetMapping("/{idPatient}")
    public ResponseEntity<AntecedentClassiqueDto> getAntecedentssanteByIdPatient (@PathVariable int idPatient) throws Exception {
        AntecedentClassiqueDto antecedentAdulteEnfantDto = antecedentClassiqueService.getByIdPatient(idPatient);
        return new ResponseEntity<>(antecedentAdulteEnfantDto,HttpStatus.OK);
    }

}