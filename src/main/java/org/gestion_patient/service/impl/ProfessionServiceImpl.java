package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.entity.Profession;
import org.gestion_patient.entityDto.ProfessionDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.exception.RessourceAlreadyexistsException;
import org.gestion_patient.mapper.ProfessionMapper;
import org.gestion_patient.repository.ProfessionRepository;
import org.gestion_patient.service.ProfessionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessionServiceImpl implements ProfessionService {
    private ProfessionRepository professionRepository;


    @Override
    public List<ProfessionDto> findAll() {
        List<Profession> professions = professionRepository.findAll();
        return professions.stream().map(ProfessionMapper::mapToProfessionDto).toList();
    }

    @Override
    public ProfessionDto getById(int id) {
        Profession profession = professionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Medecin not found with given id " + id));
        return ProfessionMapper.mapToProfessionDto(profession);
    }

    public ProfessionDto create(ProfessionDto professionDto){
        Profession profession = professionRepository.findByLibelleProfession(professionDto.getLibelleProfession());
        if(profession != null){
            throw new RessourceAlreadyexistsException ("this profession already exists");
        }
        Profession professionToSave = ProfessionMapper.mapToProfession(professionDto);
        Profession professionSaved = professionRepository.save(professionToSave);
        return ProfessionMapper.mapToProfessionDto(professionSaved);
    }
}
