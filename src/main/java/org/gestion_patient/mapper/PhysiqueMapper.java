package org.gestion_patient.mapper;

import org.gestion_patient.Data.DataUtil;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entity.Physique;
import org.gestion_patient.entityDto.PhysiqueDto;

public class PhysiqueMapper {
    public static PhysiqueDto mapToPhysiqueDto (Physique physique){
        return new PhysiqueDto(
                physique.getIdPhysique(),
                physique.getDateMesure(),
                DataUtil.displayFloat(physique.getPoids()),
                DataUtil.displayFloat(physique.getTaille()),
                DataUtil.displayBoolean(physique.getDroitier()),
                DataUtil.displayBoolean(physique.getLunettes()),
                DataUtil.displayBoolean(physique.getDentaire()),
                physique.getPatient().getIdPatient()
        );
    }

    public static Physique mapToPhysique (PhysiqueDto physiqueDto, Patient patient){
        return new Physique(
                physiqueDto.getIdPhysique(),
                physiqueDto.getDateMesure(),
                DataUtil.displayFloat(physiqueDto.getPoids()),
                DataUtil.displayFloat(physiqueDto.getTaille()),
                DataUtil.displayBoolean(physiqueDto.getDroitier()),
                DataUtil.displayBoolean(physiqueDto.getLunettes()),
                DataUtil.displayBoolean(physiqueDto.getDentaire()),
                patient);
    }



}
