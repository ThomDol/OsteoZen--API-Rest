package org.gestion_patient.service;

import org.gestion_patient.entityDto.PostAccouchementDto;

public interface PostAccouchementService {
    PostAccouchementDto create(PostAccouchementDto postAccouchementDto, int idPatient) ;
    PostAccouchementDto update(int id,PostAccouchementDto postAccouchementDtoUpdated) ;
    PostAccouchementDto getByIdAccouchement(int id) ;
    PostAccouchementDto getById (int id);
}
