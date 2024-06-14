package org.gestion_patient.service;

import org.gestion_patient.entityDto.SportDto;

import java.util.List;

public interface SportService {
    List<SportDto> findAll() ;
    SportDto findById(int idSport);
    SportDto create(SportDto sportDto);



}
