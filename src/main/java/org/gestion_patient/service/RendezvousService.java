package org.gestion_patient.service;

import org.gestion_patient.entityDto.RendezvousDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RendezvousService {
     List<RendezvousDto> findAllByPatient(int idPatient);
     RendezvousDto findById(int id) throws Exception;
    RendezvousDto createRendezVousByPatient(RendezvousDto rendezvousDto,int idPatient) throws Exception;
    void deleteRendezvous(int id);
   RendezvousDto  update(int id, RendezvousDto rendezvousDto) throws Exception;


}





