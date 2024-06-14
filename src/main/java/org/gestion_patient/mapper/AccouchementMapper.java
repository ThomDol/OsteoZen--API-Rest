package org.gestion_patient.mapper;

import org.gestion_patient.entity.Accouchement;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entity.PostAccouchement;
import org.gestion_patient.entityDto.AccouchementDto;

public class AccouchementMapper {
    public static AccouchementDto mapToAccouchementDto (Accouchement accouchement){
        return new AccouchementDto(
                accouchement.getIdAccouchement(),
                accouchement.getDateAccouchement(),
                displayInt(accouchement.getDureeTravail()),
                displayString(accouchement.getDifficulteTravail()),
                displayBoolean(accouchement.getCesarienne()),
                displayBoolean(accouchement.getPeridurale()),
                displayBoolean(accouchement.getAccouchementProvoque()),
                displayBoolean(accouchement.getExtractionInstrumentale()),
                displayBoolean(accouchement.getOcytocine()),
                displayBoolean(accouchement.getCirculaireDuCordonOmbilical()),
                displayBoolean(accouchement.getAideManuellePoussee()),
                displayString(accouchement.getComplication()),
                displayBoolean(accouchement.getEpisiotomie()),
                displayBoolean(accouchement.getDechirure()),
                displayBoolean(accouchement.getReeducationPerinee()),
                displayString(accouchement.getPresentationAAccouchement()),
                displayInt(accouchement.getAgeDateAccouchement()),
                accouchement.getPatient().getIdPatient()
        );
    }
    public static Accouchement mapToAccouchement (AccouchementDto accouchementDto , Patient patient){
        return new Accouchement(
                accouchementDto.getIdAccouchement(),
                accouchementDto.getDateAccouchement(),
                displayInt(accouchementDto.getDureeTravail()),
                displayString(accouchementDto.getDifficulteTravail()),
                displayBoolean(accouchementDto.getCesarienne()),
                displayBoolean(accouchementDto.getPeridurale()),
                displayBoolean(accouchementDto.getAccouchementProvoque()),
                displayBoolean(accouchementDto.getExtractionInstrumentale()),
                displayBoolean(accouchementDto.getOcytocine()),
                displayBoolean(accouchementDto.getCirculaireDuCordonOmbilical()),
                displayBoolean(accouchementDto.getAideManuellePoussee()),
                displayString(accouchementDto.getComplication()),
                displayBoolean(accouchementDto.getEpisiotomie()),
                displayBoolean(accouchementDto.getDechirure()),
                displayBoolean(accouchementDto.getReeducationPerinee()),
                displayString(accouchementDto.getPresentationAAccouchement()),
                displayInt(accouchementDto.getAgeDateAccouchement()),
                patient
        );
    }

    public static Boolean displayBoolean (Boolean elem){
        return elem!=null?elem:null;
    }
    public static String displayString (String elem){return elem!=null?elem:null;}
    public static Integer displayInt (Integer elem){return elem!=null?elem:null;}



}
