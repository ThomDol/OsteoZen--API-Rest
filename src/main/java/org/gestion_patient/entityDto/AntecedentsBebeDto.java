package org.gestion_patient.entityDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AntecedentsBebeDto {
    private int idAntecedentBebe;
    private String dateCreation;
    private String dateUpdate;
    private String maternite;
    private Float perimetreCranien;
    private Float apgar;
    private Boolean depassementDeTerme;
    private Boolean prematurite;
    private Boolean deformationDuCrane;
    private Boolean bosseSeroSanguine ;
    private Boolean cephalhematome;
    private Boolean paralysieObstetricaleDuPlexusBrachial;
    private Boolean paralysieFaciale;
    private Boolean fractureClavicule;
    private Boolean dysplasieHanche;
    private Boolean plagiocephalie;
    private Boolean torticolis;
    private Boolean refluxGastroOesophagien;
    private Boolean coliques;
    private Boolean allaitementMaternelle;
    private Integer efficaciteSuccion;
    private Boolean sucagePouce;
    private Boolean tetine;
    private String typeRespiration ;
    private Boolean presenceBruitsArticulaires;
    private Boolean tics;
    private int idPatient;


}
