package org.gestion_patient.service;



import org.gestion_patient.entityDto.GrossesseDto;

import java.util.List;

public interface GrossesseService {
    GrossesseDto create(GrossesseDto grossesseDto, int idPatient) ;
    GrossesseDto update(int id,GrossesseDto grossesseDto) ;
    List<GrossesseDto> getAllByidPatient(int id) ;

    GrossesseDto getById (int id);

}
