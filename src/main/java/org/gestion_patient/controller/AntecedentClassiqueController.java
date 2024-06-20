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
@RequestMapping("/antecedent")
public class AntecedentClassiqueController {
    private AntecedentClassiqueService antecedentAdulteEnfantServiceService;

    @PostMapping("/{idPatient}")
    public ResponseEntity<AntecedentClassiqueDto> createAntecedent (@RequestBody AntecedentClassiqueDto antecedentAdulteEnfantDto, @PathVariable int idPatient) throws Exception {
        AntecedentClassiqueDto antecedentAdulteEnfantDtoSaved = antecedentAdulteEnfantServiceService.create(antecedentAdulteEnfantDto,idPatient);
        return new ResponseEntity<>(antecedentAdulteEnfantDtoSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{idPatient}/{idAntecedent}")
    public ResponseEntity<AntecedentClassiqueDto> updateAntecedent (@PathVariable int idAntecedent,@PathVariable int idPatient, @RequestBody AntecedentClassiqueDto antecedentAdulteEnfantDto ) throws Exception {
        AntecedentClassiqueDto antecedentAdulteEnfantDtoUpdated = antecedentAdulteEnfantServiceService.update(idAntecedent,idPatient,antecedentAdulteEnfantDto);
        return new ResponseEntity<>(antecedentAdulteEnfantDtoUpdated,HttpStatus.OK);
    }

    @GetMapping("/{idPatient}/{id}")
    public ResponseEntity<AntecedentClassiqueDto> getAntecedentssanteByIdPatient (@PathVariable int idPatient,@PathVariable int id) throws Exception {
        AntecedentClassiqueDto antecedentAdulteEnfantDto = antecedentAdulteEnfantServiceService.getByIdAndIdPatient(id,idPatient);
        return new ResponseEntity<>(antecedentAdulteEnfantDto,HttpStatus.OK);
    }

}