package org.gestion_patient.service;

import org.gestion_patient.entityDto.RendezvousDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RendezvousService {
     List<RendezvousDto> findAllByPatientAndPraticien(int idPatient,int idPraticien);
     RendezvousDto findByIdAndIdPraticien(int id,int idPraticien) throws Exception;
    RendezvousDto createRendezVousByPatient(RendezvousDto rendezvousDto,int idPatient) throws Exception;
    void deleteRendezvous(int id,int idPatient);
   RendezvousDto  update(int id,int idPatient, RendezvousDto rendezvousDto) throws Exception;


}





