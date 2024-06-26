package org.gestion_patient.controller;


import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.AntecedentsBebeDto;
import org.gestion_patient.service.AntecedentsBebeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/antecedentbebe")
public class AntecedentBebeController {
    private AntecedentsBebeService antecedentBebeService;

    @PostMapping("/{idPatient}")
    public ResponseEntity<AntecedentsBebeDto> createAntecedentBebe (@RequestBody AntecedentsBebeDto antecedentBebeDto, @PathVariable int idPatient) throws Exception {
        AntecedentsBebeDto antecedentBebeDtoSaved = antecedentBebeService.create(antecedentBebeDto,idPatient);
        return new ResponseEntity<>(antecedentBebeDtoSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{idAntecedetnBebe}")
    public ResponseEntity<AntecedentsBebeDto> updateAntecedentBebe (@PathVariable int idAntecedetnBebe, @RequestBody AntecedentsBebeDto antecedentBebeDto ) throws Exception {
        AntecedentsBebeDto antecedentBebeDtoUpdated = antecedentBebeService.update(idAntecedetnBebe,antecedentBebeDto);
        return new ResponseEntity<>(antecedentBebeDtoUpdated,HttpStatus.OK);
    }

    @GetMapping("/{idPatient}")
    public ResponseEntity<AntecedentsBebeDto> getAntecedentBebeByIdPatient (@PathVariable int idPatient) throws Exception {
        AntecedentsBebeDto antecedentBebeDto = antecedentBebeService.getByIdPatient(idPatient);
        return new ResponseEntity<>(antecedentBebeDto,HttpStatus.OK);
    }

    @DeleteMapping("/{idAntecedentBebe}")
    public ResponseEntity<String> deleteAntecedentBebe (@PathVariable int idAntecedetnBebe){
        antecedentBebeService.deleteAntecedentBebe(idAntecedetnBebe);
        return new ResponseEntity<>("deleted with success",HttpStatus.OK);
    }

}

