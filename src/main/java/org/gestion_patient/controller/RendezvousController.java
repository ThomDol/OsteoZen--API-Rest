package org.gestion_patient.controller;

import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.RendezvousDto;
import org.gestion_patient.service.PatientService;
import org.gestion_patient.service.RendezvousService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/rendezvous")
public class RendezvousController {

    private RendezvousService rendezvousService;


  @PostMapping("/{idPatient}")
  public ResponseEntity<RendezvousDto> createRendezVousByPatient ( @RequestBody RendezvousDto rendezvousDto,@PathVariable ("idPatient") int idPatient) throws Exception {
   RendezvousDto rendezVousDtoCreated = rendezvousService.createRendezVousByPatient(rendezvousDto,idPatient);
   return new ResponseEntity<>(rendezVousDtoCreated , HttpStatus.CREATED );
 }

    @GetMapping("/{id}")
    public ResponseEntity <RendezvousDto> getRendezVousById ( @PathVariable ("id") int id) throws Exception {
    RendezvousDto getRendezVous = rendezvousService.findById(id);
    return new ResponseEntity<>( getRendezVous, HttpStatus.OK );
    }


    @GetMapping("/all/{idPatient}")
    public ResponseEntity<List<RendezvousDto>> getAllRendezVousByPatient(@PathVariable ("idPatient") int idPatient ){
    List<RendezvousDto> listRendezVous = rendezvousService.findAllByPatient(idPatient);
    return new ResponseEntity<>(listRendezVous, HttpStatus.OK );
    }

    @PutMapping ("/{id}")
     public  ResponseEntity<String> updateRendezVous (@PathVariable ("id") int id , @RequestBody RendezvousDto rendezvousDto) throws Exception {
    rendezvousService.update(id,rendezvousDto);
    return new ResponseEntity<>("Rendez-vous updated with success !", HttpStatus.OK );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRendezVous (@PathVariable ("id") int id){
    rendezvousService.deleteRendezvous(id);
    return new ResponseEntity<>("Rendez-vous deleted with success !", HttpStatus.OK );
    }
 }





