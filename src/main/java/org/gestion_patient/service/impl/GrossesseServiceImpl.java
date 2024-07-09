package org.gestion_patient.service.impl;

import jakarta.validation.OverridesAttribute;
import lombok.AllArgsConstructor;
import org.gestion_patient.entity.Grossesse;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entityDto.GrossesseDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.mapper.GrossesseMapper;
import org.gestion_patient.repository.GrossesseRepository;
import org.gestion_patient.repository.PatientRepository;
import org.gestion_patient.service.GrossesseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GrossesseServiceImpl implements GrossesseService {
    private GrossesseRepository grossesseRepository;
    private PatientRepository patientRepository;


    @Override
    public GrossesseDto create(GrossesseDto grossesseDto, int idPatient) {
        Patient patient = patientRepository.findById(idPatient).orElseThrow(()->new ResourceNotFoundException("Patient with id"+idPatient+" doesn't exist"));
        Grossesse grossesse = GrossesseMapper.mapToGrossesse(grossesseDto,patient);
        return GrossesseMapper.mapToGrossesseDto(grossesseRepository.save(grossesse));
    }

    @Override
    public GrossesseDto update(int idToUpdate, GrossesseDto grossesseDto) {
        Grossesse grossesseToUpdate = grossesseRepository.findById(idToUpdate).orElseThrow(()->new ResourceNotFoundException("Grossesse doesn't exist"));
        System.out.println(grossesseToUpdate.getDateUpdate());
        if(grossesseDto.getDateUpdate()!=null){
            grossesseToUpdate.setDateUpdate(grossesseDto.getDateUpdate());
        }

        if (grossesseDto.getMaternite() != null) {
                grossesseToUpdate.setMaternite(grossesseDto.getMaternite());
            }
            if (grossesseDto.getGrossesseMultiple() != null) {
                grossesseToUpdate.setGrossesseMultiple(grossesseDto.getGrossesseMultiple());
            }
            if (grossesseDto.getDouleursPendantGrossesse() != null) {
                grossesseToUpdate.setDouleursPendantGrossesse(grossesseDto.getDouleursPendantGrossesse());
            }
            if (grossesseDto.getEtatPsychoEmotionnel() != null) {
                grossesseToUpdate.setEtatPsychoEmotionnel(grossesseDto.getEtatPsychoEmotionnel());
            }
            if (grossesseDto.getTraitementLieGrossesse() != null) {
                grossesseToUpdate.setTraitementLieGrossesse(grossesseDto.getTraitementLieGrossesse());
            }
            if (grossesseDto.getMouvementsBebe() != null) {
                grossesseToUpdate.setMouvementsBebe(grossesseDto.getMouvementsBebe());
            }
            if (grossesseDto.getCesariennePrevue() != null) {
                grossesseToUpdate.setCesariennePrevue(grossesseDto.getCesariennePrevue());
            }
            if (grossesseDto.getProjetPeridurale() != null) {
                grossesseToUpdate.setProjetPeridurale(grossesseDto.getProjetPeridurale());
            }
            if (grossesseDto.getProjetAllaitement() != null) {
                grossesseToUpdate.setProjetAllaitement(grossesseDto.getProjetAllaitement());
            }
            if (grossesseDto.getNausees() != null) {
                grossesseToUpdate.setNausees(grossesseDto.getNausees());
            }
            if (grossesseDto.getConstipation() != null) {
                grossesseToUpdate.setConstipation(grossesseDto.getConstipation());
            }
            if (grossesseDto.getDiarrhees() != null) {
                grossesseToUpdate.setDiarrhees(grossesseDto.getDiarrhees());
            }
            if (grossesseDto.getAigreursEstomac() != null) {
                grossesseToUpdate.setAigreursEstomac(grossesseDto.getAigreursEstomac());
            }
            if (grossesseDto.getOedemesMembresInferieurs() != null) {
                grossesseToUpdate.setOedemesMembresInferieurs(grossesseDto.getOedemesMembresInferieurs());
            }
            if (grossesseDto.getPesanteurPelvienne() != null) {
                grossesseToUpdate.setPesanteurPelvienne(grossesseDto.getPesanteurPelvienne());
            }
            if (grossesseDto.getIncontinence() != null) {
                grossesseToUpdate.setIncontinence(grossesseDto.getIncontinence());
            }
            if (grossesseDto.getTensionMammaire() != null) {
                grossesseToUpdate.setTensionMammaire(grossesseDto.getTensionMammaire());
            }
            if (grossesseDto.getMastose() != null) {
                grossesseToUpdate.setMastose(grossesseDto.getMastose());
            }

            Grossesse grossesseUpdated = grossesseRepository.save(grossesseToUpdate);
            return GrossesseMapper.mapToGrossesseDto(grossesseUpdated);
    }

    @Override
    public List<GrossesseDto> getAllByidPatient(int idPatient) {
        List<Grossesse> grossesses = grossesseRepository.findByPatientIdPatient(idPatient);
        return grossesses.stream().map(GrossesseMapper::mapToGrossesseDto).toList();
    }

    @Override
    public GrossesseDto getById(int id) {
        Grossesse grossesse = grossesseRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Grossesse doesn't exist"));
            return GrossesseMapper.mapToGrossesseDto(grossesse);
    }

    @Override
    public void deleteGrossesse(int id){
        Grossesse grossesseToDelete = grossesseRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Grossesse doesn't exist"));
        grossesseRepository.delete(grossesseToDelete);
    }

    }



