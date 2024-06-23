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
  public ResponseEntity<RendezvousDto> createRendezVousByPatient ( @RequestBody RendezvousDto rendezvousDto,@PathVariable int idPatient) throws Exception {
   RendezvousDto rendezVousDtoCreated = rendezvousService.createRendezVousByPatient(rendezvousDto,idPatient);
   return new ResponseEntity<>(rendezVousDtoCreated , HttpStatus.CREATED );
 }

    @GetMapping("/{idPraticien}/{id}")
    public ResponseEntity <RendezvousDto> getRendezVousById ( @PathVariable int id,@PathVariable int idPraticien) throws Exception {
    RendezvousDto getRendezVous = rendezvousService.findByIdAndIdPraticien(id,idPraticien);
    return new ResponseEntity<>( getRendezVous, HttpStatus.OK );
    }


    @GetMapping("/all/{idPatient}/{idPraticien}")
    public ResponseEntity<List<RendezvousDto>> getAllRendezVousByPatientAndPraticien(@PathVariable int idPatient,@PathVariable int idPraticien){
    List<RendezvousDto> listRendezVous = rendezvousService.findAllByPatientAndPraticien(idPatient,idPraticien);
    return new ResponseEntity<>(listRendezVous, HttpStatus.OK );
    }

    @PutMapping ("/{idPraticien}/{id}")
     public  ResponseEntity<String> updateRendezVous (@PathVariable int id ,@PathVariable int idPraticien, @RequestBody RendezvousDto rendezvousDto) throws Exception {
    rendezvousService.update(id,idPraticien, rendezvousDto);
    return new ResponseEntity<>("Rendez-vous updated with success !", HttpStatus.OK );
    }
    @DeleteMapping("/{idPraticien}/{id}")
    public ResponseEntity<String> deleteRendezVous (@PathVariable int id,@PathVariable int idPraticien){
    rendezvousService.deleteRendezvous(id,idPraticien);
    return new ResponseEntity<>("Rendez-vous deleted with success !", HttpStatus.OK );
    }
 }





