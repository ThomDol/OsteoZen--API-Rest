package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entity.Rendezvous;
import org.gestion_patient.entityDto.RendezvousDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.mapper.RendezvousMapper;
import org.gestion_patient.repository.PatientRepository;
import org.gestion_patient.repository.RendezvousRepository;
import org.gestion_patient.service.RendezvousService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RendezvousServiceImpl implements RendezvousService {
    private RendezvousRepository rendezvousRepository;
    private PatientRepository patientRepository;

    @Override
    public List<RendezvousDto> findAllByPatientAndPraticien(int idPatient,int idPraticien) {
        List<Rendezvous> rendezvousList = rendezvousRepository.findAllByPatientIdPatientAndPatientPraticienIdPraticien(idPatient,idPraticien);
        return rendezvousList.stream().map(rendezvous-> {
            try {
                return RendezvousMapper.mapToRendezvousDto(rendezvous);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();

    }

    @Override
    public RendezvousDto findById(int id) throws Exception {
        Rendezvous rendezvous = rendezvousRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rendezvous not found with given id!" + id));
        return RendezvousMapper.mapToRendezvousDto(rendezvous);
    }

    @Override
    public RendezvousDto createRendezVousByPatient(RendezvousDto rendezvousDto,int idPatient) throws Exception {
        Patient patient = patientRepository.findById(idPatient).orElseThrow(() -> new ResourceNotFoundException("Patient not found with given id: " + rendezvousDto.getIdPatient()));
        Rendezvous rendezvous = RendezvousMapper.maptoRendezvous(rendezvousDto, patient);
        return RendezvousMapper.mapToRendezvousDto(rendezvousRepository.save(rendezvous));

    }

    @Override
    public void deleteRendezvous(int id) {
        if (!rendezvousRepository.existsById(id)) {
            throw new ResourceNotFoundException("Rendezvous not found with given id!" + id);
        }
        rendezvousRepository.deleteById(id);
    }

    @Override
    public RendezvousDto update(int id, RendezvousDto rendezvousDto) throws Exception {
        Rendezvous rendezvous = rendezvousRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Rendezvous not found with given id: " + id));
        rendezvous.setSyntheseRendezVous(Crypto.cryptService(rendezvousDto.getSyntheseRendezVous()));
        return RendezvousMapper.mapToRendezvousDto(rendezvousRepository.save(rendezvous));


    }

}
