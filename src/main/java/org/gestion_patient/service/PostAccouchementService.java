package org.gestion_patient.service;

import org.gestion_patient.entityDto.PostAccouchementDto;

public interface PostAccouchementService {
    PostAccouchementDto createByAccouchement(PostAccouchementDto postAccouchementDto, int idAccouchement) ;
    PostAccouchementDto update(int id,PostAccouchementDto postAccouchementDtoUpdated) ;
    PostAccouchementDto getByIdAccouchement(int id) ;
    PostAccouchementDto getById (int id);
    void delete (int id);
}
