package org.gestion_patient.service;



import org.gestion_patient.entityDto.PhysiqueDto;

import java.util.List;

public interface PhysiqueService {
    List<PhysiqueDto> getAllByIdPatient(int idPatient );
    PhysiqueDto create(PhysiqueDto physiqueDto , int idPatient );
    PhysiqueDto update(int id,PhysiqueDto physiqueDtoUpdated);
    PhysiqueDto getByIdPhysyque(int id);
    void deletePhysique (int id);

}
