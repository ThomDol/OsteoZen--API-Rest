package org.gestion_patient.mapper;

import org.gestion_patient.Data.DataUtil;
import org.gestion_patient.entity.Grossesse;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entityDto.GrossesseDto;

public class GrossesseMapper {
    public static GrossesseDto mapToGrossesseDto(Grossesse grossesse) {
        return new GrossesseDto(
                grossesse.getIdGrossesse(),
                grossesse.getDateCreation(),
                grossesse.getDateUpdate(),
                grossesse.getMaternite(),
                DataUtil.displayBoolean(grossesse.getGrossesseMultiple()),
                DataUtil.displayString(grossesse.getDouleursPendantGrossesse()),
                DataUtil.displayString(grossesse.getEtatPsychoEmotionnel()),
                DataUtil.displayString(grossesse.getTraitementLieGrossesse()),
                DataUtil.displayString(grossesse.getMouvementsBebe()),
                DataUtil.displayBoolean(grossesse.getCesariennePrevue()),
                DataUtil.displayBoolean(grossesse.getProjetPeridurale()),
                DataUtil.displayBoolean(grossesse.getProjetAllaitement()),
                DataUtil.displayBoolean(grossesse.getNausees()),
                DataUtil.displayBoolean(grossesse.getConstipation()),
                DataUtil.displayBoolean(grossesse.getDiarrhees()),
                DataUtil.displayBoolean(grossesse.getAigreursEstomac()),
                DataUtil.displayBoolean(grossesse.getOedemesMembresInferieurs()),
                DataUtil.displayBoolean(grossesse.getPesanteurPelvienne()),
                DataUtil.displayBoolean(grossesse.getIncontinence()),
                DataUtil.displayBoolean(grossesse.getTensionMammaire()),
                DataUtil.displayBoolean(grossesse.getMastose()),
                grossesse.getPatient().getIdPatient());
    }

    public static Grossesse mapToGrossesse (GrossesseDto grossesseDto, Patient patient){
        return new Grossesse(
                grossesseDto.getIdGrossesse(),
                grossesseDto.getDateCreation(),
                grossesseDto.getDateUpdate(),
                grossesseDto.getMaternite(),
                DataUtil.displayBoolean(grossesseDto.getGrossesseMultiple()),
                DataUtil.displayString(grossesseDto.getDouleursPendantGrossesse()),
                DataUtil.displayString(grossesseDto.getEtatPsychoEmotionnel()),
                DataUtil.displayString(grossesseDto.getTraitementLieGrossesse()),
                DataUtil.displayString(grossesseDto.getMouvementsBebe()),
                DataUtil.displayBoolean(grossesseDto.getCesariennePrevue()),
                DataUtil.displayBoolean(grossesseDto.getProjetPeridurale()),
                DataUtil.displayBoolean(grossesseDto.getProjetAllaitement()),
                DataUtil.displayBoolean(grossesseDto.getNausees()),
                DataUtil.displayBoolean(grossesseDto.getConstipation()),
                DataUtil.displayBoolean(grossesseDto.getDiarrhees()),
                DataUtil.displayBoolean(grossesseDto.getAigreursEstomac()),
                DataUtil.displayBoolean(grossesseDto.getOedemesMembresInferieurs()),
                DataUtil.displayBoolean(grossesseDto.getPesanteurPelvienne()),
                DataUtil.displayBoolean(grossesseDto.getIncontinence()),
                DataUtil.displayBoolean(grossesseDto.getTensionMammaire()),
                DataUtil.displayBoolean(grossesseDto.getMastose()),
                patient);
    }


}
