package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.entity.AntecedentsBebe;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entityDto.AntecedentsBebeDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.mapper.AntecedentsBebeMapper;
import org.gestion_patient.repository.AntecedentBebeRepository;
import org.gestion_patient.repository.PatientRepository;
import org.gestion_patient.service.AntecedentsBebeService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AntecedentsBebeServiceImpl implements AntecedentsBebeService {
    private AntecedentBebeRepository antecedentBebeRepository;
    private PatientRepository patientRepository;

    @Override
    public AntecedentsBebeDto create(AntecedentsBebeDto antecedentBebeDto, int idPatient) throws Exception {
        Patient patient = patientRepository.findById(idPatient).orElseThrow(()->new ResourceNotFoundException("Patient doesn't exist"));
        AntecedentsBebe antecedentBebe = AntecedentsBebeMapper.mapToAntecedentBebe(antecedentBebeDto,patient);
        return AntecedentsBebeMapper.mapToAntecedentssanteBebeDto(antecedentBebeRepository.save(antecedentBebe));
    }

    @Override
    public AntecedentsBebeDto update(int idToUpdate,int idPatient, AntecedentsBebeDto antecedentBebeDtoUpdated) throws Exception {
        AntecedentsBebe antecedentBebeToUpdate = antecedentBebeRepository.findByIdAntecedentBebeAndPatientIdPatient(idToUpdate, idPatient);
        if (antecedentBebeToUpdate != null) {
            if (antecedentBebeDtoUpdated.getMaternite() != null) {
                antecedentBebeToUpdate.setMaternite(antecedentBebeDtoUpdated.getMaternite());
            }
            if (antecedentBebeDtoUpdated.getPerimetreCranien() != null) {
                antecedentBebeToUpdate.setPerimetreCranien(antecedentBebeDtoUpdated.getPerimetreCranien());
            }
            if (antecedentBebeDtoUpdated.getApgar() != null) {
                antecedentBebeToUpdate.setApgar(antecedentBebeDtoUpdated.getApgar());
            }
            if (antecedentBebeDtoUpdated.getDepassementDeTerme() != null) {
                antecedentBebeToUpdate.setDepassementDeTerme(antecedentBebeDtoUpdated.getDepassementDeTerme());
            }
            if (antecedentBebeDtoUpdated.getPrematurite() != null) {
                antecedentBebeToUpdate.setPrematurite(antecedentBebeDtoUpdated.getPrematurite());
            }
            if (antecedentBebeDtoUpdated.getDeformationDuCrane() != null) {
                antecedentBebeToUpdate.setDeformationDuCrane(antecedentBebeDtoUpdated.getDeformationDuCrane());
            }
            if (antecedentBebeDtoUpdated.getBosseSeroSanguine() != null) {
                antecedentBebeToUpdate.setBosseSeroSanguine(antecedentBebeDtoUpdated.getBosseSeroSanguine());
            }
            if (antecedentBebeDtoUpdated.getCephalhematome() != null) {
                antecedentBebeToUpdate.setCephalhematome(antecedentBebeDtoUpdated.getCephalhematome());
            }
            if (antecedentBebeDtoUpdated.getParalysieObstetricaleDuPlexusBrachial() != null) {
                antecedentBebeToUpdate.setParalysieObstetricaleDuPlexusBrachial(antecedentBebeDtoUpdated.getParalysieObstetricaleDuPlexusBrachial());
            }
            if (antecedentBebeDtoUpdated.getParalysieFaciale() != null) {
                antecedentBebeToUpdate.setParalysieFaciale(antecedentBebeDtoUpdated.getParalysieFaciale());
            }
            if (antecedentBebeDtoUpdated.getFractureClavicule() != null) {
                antecedentBebeToUpdate.setFractureClavicule(antecedentBebeDtoUpdated.getFractureClavicule());
            }
            if (antecedentBebeDtoUpdated.getDysplasieHanche() != null) {
                antecedentBebeToUpdate.setDysplasieHanche(antecedentBebeDtoUpdated.getDysplasieHanche());
            }
            if (antecedentBebeDtoUpdated.getPlagiocephalie() != null) {
                antecedentBebeToUpdate.setPlagiocephalie(antecedentBebeDtoUpdated.getPlagiocephalie());
            }
            if (antecedentBebeDtoUpdated.getTorticolis() != null) {
                antecedentBebeToUpdate.setTorticolis(antecedentBebeDtoUpdated.getTorticolis());
            }
            if (antecedentBebeDtoUpdated.getRefluxGastroOesophagien() != null) {
                antecedentBebeToUpdate.setRefluxGastroOesophagien(antecedentBebeDtoUpdated.getRefluxGastroOesophagien());
            }
            if (antecedentBebeDtoUpdated.getColiques() != null) {
                antecedentBebeToUpdate.setColiques(antecedentBebeDtoUpdated.getColiques());
            }
            if (antecedentBebeDtoUpdated.getAllaitementMaternelle() != null) {
                antecedentBebeToUpdate.setAllaitementMaternelle(antecedentBebeDtoUpdated.getAllaitementMaternelle());
            }
            if (antecedentBebeDtoUpdated.getEfficaciteSuccion() != null) {
                antecedentBebeToUpdate.setEfficaciteSuccion(antecedentBebeDtoUpdated.getEfficaciteSuccion());
            }
            if (antecedentBebeDtoUpdated.getSucagePouce() != null) {
                antecedentBebeToUpdate.setSucagePouce(antecedentBebeDtoUpdated.getSucagePouce());
            }
            if (antecedentBebeDtoUpdated.getTetine() != null) {
                antecedentBebeToUpdate.setTetine(antecedentBebeDtoUpdated.getTetine());
            }
            if (antecedentBebeDtoUpdated.getTypeRespiration() != null) {
                antecedentBebeToUpdate.setTypeRespiration(antecedentBebeDtoUpdated.getTypeRespiration());
            }
            if (antecedentBebeDtoUpdated.getPresenceBruitsArticulaires() != null) {
                antecedentBebeToUpdate.setPresenceBruitsArticulaires(antecedentBebeDtoUpdated.getPresenceBruitsArticulaires());
            }
            if (antecedentBebeDtoUpdated.getTics() != null) {
                antecedentBebeToUpdate.setTics(antecedentBebeDtoUpdated.getTics());
            }

            AntecedentsBebe antecedentBebeUpdated = antecedentBebeRepository.save(antecedentBebeToUpdate);
            return AntecedentsBebeMapper.mapToAntecedentssanteBebeDto(antecedentBebeUpdated);
        } else {
            throw new ResourceNotFoundException("AntecedentBebe doesn't exist");
        }
    }

    @Override
    public AntecedentsBebeDto getByIdAndIdPatient(int id,int idPatient) throws Exception {
        AntecedentsBebe antecedentBebe = antecedentBebeRepository.findByIdAntecedentBebeAndPatientIdPatient(id,idPatient);
        if(antecedentBebe!=null){
            return AntecedentsBebeMapper.mapToAntecedentssanteBebeDto(antecedentBebe);
        }
        else{throw new ResourceNotFoundException("AntecedentBebe doesn't exist");}
    }
}