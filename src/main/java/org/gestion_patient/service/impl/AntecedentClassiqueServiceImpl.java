package org.gestion_patient.service.impl;


import lombok.AllArgsConstructor;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.AntecedentClassique;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entityDto.AntecedentClassiqueDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.mapper.AntecedentClassiqueMapper;
import org.gestion_patient.repository.AntecedentClassiqueRepository;
import org.gestion_patient.repository.PatientRepository;
import org.gestion_patient.service.AntecedentClassiqueService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AntecedentClassiqueServiceImpl implements AntecedentClassiqueService {
    private AntecedentClassiqueRepository antecedentClassiqueRepository;
    private PatientRepository patientRepository;

    @Override
    public AntecedentClassiqueDto create(AntecedentClassiqueDto antecedentClassiqueDto, int idPatient) throws Exception {
        Patient patient = patientRepository.findById(idPatient).orElseThrow(()->new ResourceNotFoundException("Patient doesn't exist"));
        AntecedentClassique antecedentClassque = AntecedentClassiqueMapper.mapToAntecedentAdulteEnfant(antecedentClassiqueDto,patient);
        return AntecedentClassiqueMapper.mapToAntecedentAdulteEnfantDto(antecedentClassiqueRepository.save(antecedentClassque));
    }

    @Override
    public AntecedentClassiqueDto update(int idToUpdate,  AntecedentClassiqueDto antecedentClassiqueDtoUpdated) throws Exception {
        AntecedentClassique antecedentClassiqueToUpdate = antecedentClassiqueRepository.findById(idToUpdate).orElseThrow(()->new ResourceNotFoundException("AntecedentClassique doesn't exist"));
                antecedentClassiqueToUpdate.setDateUpdate(antecedentClassiqueDtoUpdated.getDateUpdate());
            if (antecedentClassiqueDtoUpdated.getGrossesse() != null) {
                antecedentClassiqueToUpdate.setGrossesse(antecedentClassiqueDtoUpdated.getGrossesse());
            }
            if (antecedentClassiqueDtoUpdated.getFumeur() != null) {
                antecedentClassiqueToUpdate.setFumeur(antecedentClassiqueDtoUpdated.getFumeur());
            }
            if (antecedentClassiqueDtoUpdated.getAllergie() != null) {
                antecedentClassiqueToUpdate.setAllergie(antecedentClassiqueDtoUpdated.getAllergie());
            }
            if (antecedentClassiqueDtoUpdated.getTraitement() != null) {
                antecedentClassiqueToUpdate.setTraitement(antecedentClassiqueDtoUpdated.getTraitement());
            }
            if (antecedentClassiqueDtoUpdated.getAntTraumatique() != null) {
                antecedentClassiqueToUpdate.setAntTraumatique(Crypto.cryptService(antecedentClassiqueDtoUpdated.getAntTraumatique()));
            }
            if (antecedentClassiqueDtoUpdated.getAntChirurgicaux() != null) {
                antecedentClassiqueToUpdate.setAntChirurgicaux(Crypto.cryptService(antecedentClassiqueDtoUpdated.getAntChirurgicaux()));
            }
            if (antecedentClassiqueDtoUpdated.getAntFamilliaux() != null) {
                antecedentClassiqueToUpdate.setAntFamilliaux(Crypto.cryptService(antecedentClassiqueDtoUpdated.getAntFamilliaux()));
            }
            if (antecedentClassiqueDtoUpdated.getAntOrl() != null) {
                antecedentClassiqueToUpdate.setAntOrl(Crypto.cryptService(antecedentClassiqueDtoUpdated.getAntOrl()));
            }
            if (antecedentClassiqueDtoUpdated.getAntVisceral() != null) {
                antecedentClassiqueToUpdate.setAntVisceral(Crypto.cryptService(antecedentClassiqueDtoUpdated.getAntVisceral()));
            }
            if (antecedentClassiqueDtoUpdated.getAntCardioPulmonaire() != null) {
                antecedentClassiqueToUpdate.setAntCardioPulmonaire(Crypto.cryptService(antecedentClassiqueDtoUpdated.getAntCardioPulmonaire()));
            }
            if (antecedentClassiqueDtoUpdated.getAntUroGynecaux() != null) {
                antecedentClassiqueToUpdate.setAntUroGynecaux(Crypto.cryptService(antecedentClassiqueDtoUpdated.getAntUroGynecaux()));
            }
            if (antecedentClassiqueDtoUpdated.getAntPsy() != null) {
                antecedentClassiqueToUpdate.setAntPsy(Crypto.cryptService(antecedentClassiqueDtoUpdated.getAntPsy()));
            }
            if (antecedentClassiqueDtoUpdated.getAntNotesDiverses() != null) {
                antecedentClassiqueToUpdate.setAntNotesDiverses(Crypto.cryptService(antecedentClassiqueDtoUpdated.getAntNotesDiverses()));
            }
            org.gestion_patient.entity.AntecedentClassique antecedentAdulteEnfantUpdated = antecedentClassiqueRepository.save(antecedentClassiqueToUpdate);
            return AntecedentClassiqueMapper.mapToAntecedentAdulteEnfantDto(antecedentAdulteEnfantUpdated);

    }

    @Override
    public AntecedentClassiqueDto getByIdPatient(int idPatient) throws Exception {
        AntecedentClassique antecedentAdulteEnfant = antecedentClassiqueRepository.findByPatientIdPatient(idPatient);
        if(antecedentAdulteEnfant!=null){
            return AntecedentClassiqueMapper.mapToAntecedentAdulteEnfantDto(antecedentAdulteEnfant);
        }
        else{return null;}
    }
}