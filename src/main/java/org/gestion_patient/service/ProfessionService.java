package org.gestion_patient.service;


import org.gestion_patient.entityDto.ProfessionDto;

import java.util.List;

public interface ProfessionService {
    List<ProfessionDto> findAll();
    ProfessionDto getById(int id);
    ProfessionDto create(ProfessionDto professionDto);

}
