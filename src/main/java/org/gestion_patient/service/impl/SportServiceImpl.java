package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.entity.Sport;
import org.gestion_patient.entityDto.SportDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.mapper.SportMapper;
import org.gestion_patient.repository.SportRepository;
import org.gestion_patient.service.SportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SportServiceImpl implements SportService {
    private SportRepository sportRepository;


    @Override
    public List<SportDto> findAll() {
        List<Sport> sports = sportRepository.findAll();
        return sports.stream().map(SportMapper::mapToSportDto).toList();
    }

    @Override
    public SportDto findById(int idSport) {
        Sport sport = sportRepository.findById(idSport).orElseThrow(()->new ResourceNotFoundException("Sport not found with id"+idSport));
        return SportMapper.mapToSportDto(sport);
    }

    @Override
    public SportDto create(SportDto sportDto) {
        return SportMapper.mapToSportDto(sportRepository.save(SportMapper.mapToSport(sportDto)));
    }
}
