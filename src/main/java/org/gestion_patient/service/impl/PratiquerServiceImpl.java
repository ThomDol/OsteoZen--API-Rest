package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entity.Pratiquer;
import org.gestion_patient.entity.PratiquerId;
import org.gestion_patient.entity.Sport;
import org.gestion_patient.entityDto.PratiquerDto;
import org.gestion_patient.entityDto.SportDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.mapper.PratiquerMapper;
import org.gestion_patient.repository.PatientRepository;
import org.gestion_patient.repository.PratiquerRepository;
import org.gestion_patient.repository.SportRepository;
import org.gestion_patient.service.PratiquerService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class PratiquerServiceImpl implements PratiquerService {
    PratiquerRepository pratiquerRepository;
    SportRepository sportRepository;
    PatientRepository patientRepository;


    @Override
    public PratiquerDto createByPatient(SportDto sportDto,int idPatient) {
        Patient patient = patientRepository.findById(idPatient)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + idPatient));
        //Sport cree si pas existant via une autre requete avant soumission de cette requete
        Sport sport = sportRepository.findByNomSport(sportDto.getNomSport());
        Pratiquer pratiquerToSave = pratiquerRepository.findBySportIdSportAndPatientIdPatient(sport.getIdSport(), patient.getIdPatient());
        if (pratiquerToSave == null) {
            PratiquerId pratiquerId = new PratiquerId(sport.getIdSport(), patient.getIdPatient());
            Pratiquer pratiquer = new Pratiquer(pratiquerId, sport, patient);
            return PratiquerMapper.mapToPratiquerDto(pratiquerRepository.save(pratiquer));
        } else {
            throw new ResourceNotFoundException("Sport already added to this patient");
        }
    }


    @Override
    public List<PratiquerDto> getAllPratiquerByIdPatient(int idPatient) {
        List<Pratiquer> pratiquerList = pratiquerRepository.findAllByPatientIdPatient(idPatient);
        return pratiquerList.stream().map(PratiquerMapper::mapToPratiquerDto).toList();
    }
}
