package org.gestion_patient.entityDto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gestion_patient.entity.Patient;

import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor
@NoArgsConstructor
public class AccouchementDto {
    private int idAccouchement;
    private String dateAccouchement;
    private Integer dureeTravail;
    private String difficulteTravail;
    private Boolean accouchementProvoque ;
    private Boolean cesarienne ;
    private Boolean peridurale ;
    private Boolean extractionInstrumentale ;
    private Boolean ocytocine ;
    private Boolean circulaireDuCordonOmbilical ;
    private Boolean aideManuellePoussee ;
    private String complication;
    private Boolean episiotomie ;
    private Boolean dechirure ;
    private Boolean reeducationPerinee ;
    private String presentationAAccouchement;
    private Integer ageDateAccouchement;
    private int idPatient;


}
