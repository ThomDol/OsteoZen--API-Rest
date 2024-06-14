package org.gestion_patient.service;

import org.gestion_patient.entityDto.PraticienDto;


import java.util.List;

public interface PraticienService {
    List<PraticienDto> findAll();
    PraticienDto findById(int id) throws Exception;
    PraticienDto create(PraticienDto praticienDto) throws Exception;
    PraticienDto update(int id, PraticienDto praticienDto) throws Exception;

}
