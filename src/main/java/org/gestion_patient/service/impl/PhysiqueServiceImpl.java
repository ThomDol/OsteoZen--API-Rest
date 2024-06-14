package org.gestion_patient.service.impl;


import lombok.AllArgsConstructor;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entity.Physique;
import org.gestion_patient.entityDto.PhysiqueDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.mapper.PhysiqueMapper;
import org.gestion_patient.repository.PatientRepository;
import org.gestion_patient.repository.PhysiqueRepository;
import org.gestion_patient.service.PhysiqueService;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PhysiqueServiceImpl implements PhysiqueService {
    private PhysiqueRepository physiquerepository;
    private PatientRepository patientRepository;
    @Override
    public List<PhysiqueDto> getAllByIdPatient(int idPatient) {
        List<Physique> physiqueList = physiquerepository.findAllByPatientIdPatient(idPatient);
        return physiqueList.stream().map(PhysiqueMapper::mapToPhysiqueDto).toList();
    }

    @Override
    public PhysiqueDto create(PhysiqueDto physiqueDto, int idPatient) {
        Patient patient = patientRepository.findById(idPatient).orElseThrow(() -> new ResourceNotFoundException("Patient not found with given id " + idPatient));
        Physique physiqueToSave = PhysiqueMapper.mapToPhysique(physiqueDto, patient);
        return PhysiqueMapper.mapToPhysiqueDto(physiquerepository.save(physiqueToSave));
    }

    @Override
    public PhysiqueDto update(int id, PhysiqueDto physiqueDtoUpdated) {
        Physique physiqueToUpdate = physiquerepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Physique not found with given id " + id));
        if(physiqueDtoUpdated.getDateMesure()!=null){physiqueToUpdate.setDateMesure(physiqueDtoUpdated.getDateMesure());}
        if(physiqueDtoUpdated.getPoids()!=null){physiqueToUpdate.setPoids(physiqueDtoUpdated.getPoids());}
        if(physiqueDtoUpdated.getTaille()!=null){physiqueToUpdate.setTaille(physiqueDtoUpdated.getTaille());}
        if(physiqueDtoUpdated.getDroitier()!=null){physiqueToUpdate.setDroitier(physiqueDtoUpdated.getDroitier());}
        if(physiqueDtoUpdated.getDentaire()!=null){physiqueToUpdate.setDentaire(physiqueDtoUpdated.getDentaire());}
        if(physiqueDtoUpdated.getLunettes()!=null){physiqueToUpdate.setLunettes(physiqueDtoUpdated.getLunettes());}
        return PhysiqueMapper.mapToPhysiqueDto(physiquerepository.save(physiqueToUpdate));

    }

    @Override
    public PhysiqueDto getByIdPhysyque(int id) {
        Physique physique = physiquerepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Physique not found with given id " + id));

        return PhysiqueMapper.mapToPhysiqueDto(physique);
    }

    @Override
    public void deletePhysique(int id) {
        Physique physique = physiquerepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Physique not found with given id " + id));
        physiquerepository.delete(physique);
    }
}
