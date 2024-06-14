package org.gestion_patient.mapper;

import org.gestion_patient.Data.DataUtil;
import org.gestion_patient.entity.AntecedentsBebe;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entityDto.AntecedentsBebeDto;

public class AntecedentsBebeMapper {
    public static AntecedentsBebeDto mapToAntecedentssanteBebeDto(AntecedentsBebe antecedentBebe )  throws Exception {
        return new AntecedentsBebeDto(
                antecedentBebe.getIdAntecedentBebe(),
                antecedentBebe.getDateCreation(),
                DataUtil.displayString(antecedentBebe.getDateUpdate()),
                DataUtil.displayString(antecedentBebe.getMaternite()),
                DataUtil.displayFloat(antecedentBebe.getPerimetreCranien()),
                DataUtil.displayFloat(antecedentBebe.getApgar()),
                DataUtil.displayBoolean(antecedentBebe.getDepassementDeTerme()),
                DataUtil.displayBoolean(antecedentBebe.getPrematurite()),
                DataUtil.displayBoolean(antecedentBebe.getDeformationDuCrane()),
                DataUtil.displayBoolean(antecedentBebe.getBosseSeroSanguine()),
                DataUtil.displayBoolean(antecedentBebe.getCephalhematome()),
                DataUtil.displayBoolean(antecedentBebe.getParalysieObstetricaleDuPlexusBrachial()),
                DataUtil.displayBoolean(antecedentBebe.getParalysieFaciale()),
                DataUtil.displayBoolean(antecedentBebe.getFractureClavicule()),
                DataUtil.displayBoolean(antecedentBebe.getDysplasieHanche()),
                DataUtil.displayBoolean(antecedentBebe.getPlagiocephalie()),
                DataUtil.displayBoolean(antecedentBebe.getTorticolis()),
                DataUtil.displayBoolean(antecedentBebe.getRefluxGastroOesophagien()),
                DataUtil.displayBoolean(antecedentBebe.getColiques()),
                DataUtil.displayBoolean(antecedentBebe.getAllaitementMaternelle()),
                DataUtil.displayInt(antecedentBebe.getEfficaciteSuccion()),
                DataUtil.displayBoolean(antecedentBebe.getSucagePouce()),
                DataUtil.displayBoolean(antecedentBebe.getTetine()),
                DataUtil.displayString(antecedentBebe.getTypeRespiration()),
                DataUtil.displayBoolean(antecedentBebe.getPresenceBruitsArticulaires()),
                DataUtil.displayBoolean(antecedentBebe.getTics()),
                antecedentBebe.getPatient().getIdPatient());
    }
    public static AntecedentsBebe mapToAntecedentBebe(AntecedentsBebeDto antecedentBebeDto , Patient patient) throws Exception {
        return new AntecedentsBebe(
                antecedentBebeDto.getIdAntecedentBebe(),
                antecedentBebeDto.getDateCreation(),
                antecedentBebeDto.getDateUpdate(),
                DataUtil.displayString(antecedentBebeDto.getMaternite()),
                DataUtil.displayFloat(antecedentBebeDto.getPerimetreCranien()),
                DataUtil.displayFloat(antecedentBebeDto.getApgar()),
                DataUtil.displayBoolean(antecedentBebeDto.getDepassementDeTerme()),
                DataUtil.displayBoolean(antecedentBebeDto.getPrematurite()),
                DataUtil.displayBoolean(antecedentBebeDto.getDeformationDuCrane()),
                DataUtil.displayBoolean(antecedentBebeDto.getBosseSeroSanguine()),
                DataUtil.displayBoolean(antecedentBebeDto.getCephalhematome()),
                DataUtil.displayBoolean(antecedentBebeDto.getParalysieObstetricaleDuPlexusBrachial()),
                DataUtil.displayBoolean(antecedentBebeDto.getParalysieFaciale()),
                DataUtil.displayBoolean(antecedentBebeDto.getFractureClavicule()),
                DataUtil.displayBoolean(antecedentBebeDto.getDysplasieHanche()),
                DataUtil.displayBoolean(antecedentBebeDto.getPlagiocephalie()),
                DataUtil.displayBoolean(antecedentBebeDto.getTorticolis()),
                DataUtil.displayBoolean(antecedentBebeDto.getRefluxGastroOesophagien()),
                DataUtil.displayBoolean(antecedentBebeDto.getColiques()),
                DataUtil.displayBoolean(antecedentBebeDto.getAllaitementMaternelle()),
                DataUtil.displayInt(antecedentBebeDto.getEfficaciteSuccion()),
                DataUtil.displayBoolean(antecedentBebeDto.getSucagePouce()),
                DataUtil.displayBoolean(antecedentBebeDto.getTetine()),
                DataUtil.displayString(antecedentBebeDto.getTypeRespiration()),
                DataUtil.displayBoolean(antecedentBebeDto.getPresenceBruitsArticulaires()),
                DataUtil.displayBoolean(antecedentBebeDto.getTics()),
                patient);
    }

}
