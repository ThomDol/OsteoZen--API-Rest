package org.gestion_patient.mapper;

import org.gestion_patient.Data.DataUtil;
import org.gestion_patient.entity.Accouchement;
import org.gestion_patient.entity.PostAccouchement;
import org.gestion_patient.entity.Patient;

import org.gestion_patient.entityDto.PostAccouchementDto;

public class PostAccouchementMapper {

    public static PostAccouchementDto mapToPostAccouchementDto(PostAccouchement postAccouchement) {
        return new PostAccouchementDto(
                postAccouchement.getIdPostAccouchement(),
                postAccouchement.getDateCreation(),
                postAccouchement.getDateUpdate(),
                DataUtil.displayString(postAccouchement.getQualiteSommeil()),
                DataUtil.displayBoolean(postAccouchement.getReeducationPerinee()),
                DataUtil.displayString(postAccouchement.getInstabiliteVesicale()),
                DataUtil.displayString(postAccouchement.getEcoulementsVaginaux()),
                DataUtil.displayBoolean(postAccouchement.getRetourDeCouche()),
                DataUtil.displayBoolean(postAccouchement.getDouleurAbdominales()),
                DataUtil.displayBoolean(postAccouchement.getFievre()),
                DataUtil.displayString(postAccouchement.getInfosComplementaires()),
                postAccouchement.getAccouchement().getIdAccouchement());
    }

    public static PostAccouchement mapToPostAccouchement(PostAccouchementDto postAccouchementDto, Accouchement accouchement) {
        return new PostAccouchement(
                postAccouchementDto.getIdGrossessePostPartum(),
                postAccouchementDto.getDateCreation(),
                postAccouchementDto.getDateUpdate(),
                DataUtil.displayString(postAccouchementDto.getQualiteSommeil()),
                DataUtil.displayBoolean(postAccouchementDto.getReeducationPerinee()),
                DataUtil.displayString(postAccouchementDto.getInstabiliteVesicale()),
                DataUtil.displayString(postAccouchementDto.getEcoulementsVaginaux()),
                DataUtil.displayBoolean(postAccouchementDto.getRetourDeCouche()),
                DataUtil.displayBoolean(postAccouchementDto.getDouleurAbdominales()),
                DataUtil.displayBoolean(postAccouchementDto.getFievre()),
                DataUtil.displayString(postAccouchementDto.getInfosComplementaires()),
                accouchement
        );
    }

}
