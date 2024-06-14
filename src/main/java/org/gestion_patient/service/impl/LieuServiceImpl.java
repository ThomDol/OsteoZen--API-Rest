package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.entity.Lieu;
import org.gestion_patient.entityDto.LieuDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.exception.RessourceAlreadyexistsException;
import org.gestion_patient.mapper.LieuMapper;
import org.gestion_patient.repository.LieuRepository;
import org.gestion_patient.service.LieuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class LieuServiceImpl implements LieuService {
    private LieuRepository lieuRepository;


    @Override
    public LieuDto createLieu(LieuDto lieuDto) {
        Lieu lieu  = LieuMapper.mapToLieu(lieuDto);
       Lieu lieuToSave = lieuRepository.findByNomVilleAndCodePostal(lieuDto.getNomville(),lieuDto.getCodePostal());
        if (lieuToSave != null) {
            throw new RessourceAlreadyexistsException("Lieu already exists");
        }
       Lieu lieux = lieuRepository.save(lieu);
        return LieuMapper.mapToLieuDto(lieux);
    }

    @Override
    public LieuDto updateLieu(int id, LieuDto lieuDto) {
        Lieu lieu = lieuRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("This localisation not found with given id " + id ));
        lieu.setNomVille(lieuDto.getNomville());
        lieu.setCodePostal(lieuDto.getCodePostal());
        lieuRepository.save(lieu);
        return LieuMapper.mapToLieuDto(lieuRepository.save(lieu));
    }

    @Override
    public List<LieuDto> findAll() {
        List<Lieu> lieux = lieuRepository.findAll();
        return lieux.stream().map(LieuMapper::mapToLieuDto).toList();
    }


    @Override
    public LieuDto getLieuById(int id) {
        Lieu lieux = lieuRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("This localisation not found with given id " + id ));
        return LieuMapper.mapToLieuDto(lieux);
    }

    @Override
    public void deleteLieu(int id) {
        Lieu lieu = lieuRepository.findById(id).orElseThrow(()->new RuntimeException("This localisation not found with given id " + id ));
        lieuRepository.delete(lieu);
    }
}

