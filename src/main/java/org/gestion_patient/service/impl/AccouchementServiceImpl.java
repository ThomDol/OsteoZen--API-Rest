package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.entity.Accouchement;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entity.PostAccouchement;
import org.gestion_patient.entityDto.AccouchementDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.mapper.AccouchementMapper;
import org.gestion_patient.repository.AccouchementRepository;
import org.gestion_patient.repository.PatientRepository;
import org.gestion_patient.service.AccouchementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class AccouchementServiceImpl implements AccouchementService {
    private AccouchementRepository accouchementRepository;
    private PatientRepository patientRepository;

    @Override
    public AccouchementDto create(AccouchementDto accouchementDto, int idPatient) {
        Patient patient = patientRepository.findById(idPatient).orElseThrow(() -> new ResourceNotFoundException("Patient not found with given id " + idPatient));
        Accouchement accouchementToSave = AccouchementMapper.mapToAccouchement(accouchementDto, patient);
        return AccouchementMapper.mapToAccouchementDto(accouchementRepository.save(accouchementToSave));
    }

    //Les champs n'étant à remplir obligatoirement, test de presence avant chaque set
    @Override
    public AccouchementDto update(int id, int idPatient, AccouchementDto accouchementDto) {
        Accouchement accouchementToUpdate = accouchementRepository.findByIdAccouchementAndPatientIdPatient(id, idPatient);
        if (accouchementToUpdate != null) {
            if (accouchementDto.getDateAccouchement() != null) {
                accouchementToUpdate.setDateAccouchement(accouchementDto.getDateAccouchement());
            }
            if (accouchementDto.getDureeTravail() != null) {
                accouchementToUpdate.setDureeTravail(accouchementDto.getDureeTravail());
            }
            if (accouchementDto.getDifficulteTravail() != null) {
                accouchementToUpdate.setDifficulteTravail(accouchementDto.getDifficulteTravail());
            }
            if (accouchementDto.getCesarienne() != null) {
                accouchementToUpdate.setCesarienne(accouchementDto.getCesarienne());
            }
            if (accouchementDto.getPeridurale() != null) {
                accouchementToUpdate.setPeridurale(accouchementDto.getPeridurale());
            }
            if (accouchementDto.getExtractionInstrumentale() != null) {
                accouchementToUpdate.setExtractionInstrumentale(accouchementDto.getExtractionInstrumentale());
            }
            if (accouchementDto.getOcytocine() != null) {
                accouchementToUpdate.setOcytocine(accouchementDto.getOcytocine());
            }
            if (accouchementDto.getCirculaireDuCordonOmbilical() != null) {
                accouchementToUpdate.setCirculaireDuCordonOmbilical(accouchementDto.getCirculaireDuCordonOmbilical());
            }
            if (accouchementDto.getOcytocine() != null) {
                accouchementToUpdate.setOcytocine(accouchementDto.getOcytocine());
            }
            if (accouchementDto.getAideManuellePoussee() != null) {
                accouchementToUpdate.setAideManuellePoussee(accouchementDto.getAideManuellePoussee());
            }
            if (accouchementDto.getComplication() != null) {
                accouchementToUpdate.setComplication(accouchementDto.getComplication());
            }
            if (accouchementDto.getEpisiotomie() != null) {
                accouchementToUpdate.setEpisiotomie(accouchementDto.getEpisiotomie());
            }
            if (accouchementDto.getDechirure() != null) {
                accouchementToUpdate.setDechirure(accouchementDto.getDechirure());
            }
            if (accouchementDto.getReeducationPerinee() != null) {
                accouchementToUpdate.setReeducationPerinee(accouchementDto.getReeducationPerinee());
            }
            if (accouchementDto.getPresentationAAccouchement() != null) {
                accouchementToUpdate.setPresentationAAccouchement(accouchementDto.getPresentationAAccouchement());
            }
            if (accouchementDto.getAgeDateAccouchement() != null) {
                accouchementToUpdate.setAgeDateAccouchement(accouchementDto.getAgeDateAccouchement());
            }
            return AccouchementMapper.mapToAccouchementDto(accouchementRepository.save(accouchementToUpdate));
        } else {
            throw new ResourceNotFoundException("Accouchement not found");
        }
    }

    @Override
    public List<AccouchementDto> getAllByIdPatient(int idPatient) {
        List<Accouchement> accouchementList = accouchementRepository.findByPatientIdPatient(idPatient);
        return accouchementList.stream().map(AccouchementMapper::mapToAccouchementDto).toList();
    }


    @Override
    public AccouchementDto getByIdAccouchementAndIdPatient(int id, int idPatient) {
        Accouchement accouchement = accouchementRepository.findByIdAccouchementAndPatientIdPatient(id, idPatient);
        if (accouchement != null) {
            return AccouchementMapper.mapToAccouchementDto(accouchement);
        } else {
            throw new ResourceNotFoundException("Accouchement not found");
        }
    }

    @Override
    public void deleteAccouchement(int id, int idPatient) {
        Accouchement accouchement = accouchementRepository.findByIdAccouchementAndPatientIdPatient(id, idPatient);
        if (accouchement != null) {
            accouchementRepository.delete(accouchement);
        } else {
            throw new ResourceNotFoundException("Accouchement not found");
        }
    }

}
