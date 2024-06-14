package org.gestion_patient.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name ="Antecedent_bébé")
public class AntecedentsBebe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_antecedents", nullable = false)
    private int idAntecedentBebe;

    @Column(name = "date_creation", nullable = false,length=10)
    private String dateCreation;

    @Column(name = "date_update", nullable = true,length=10)
    private String dateUpdate;

    @Column(name = "maternite", nullable = true, length = 100)
    private String maternite;

    @Column(name = "perimetre_cranien", nullable = true)
    private Float perimetreCranien;

    @Column(name = "apgar", nullable = true)
    private Float apgar;

    @Column(name = "depassement_de_terme", nullable = true)
    private Boolean depassementDeTerme;

    @Column(name = "prematurite", nullable = true)
    private Boolean prematurite;

    @Column(name = "deformation_du_crane", nullable = true)
    private Boolean deformationDuCrane;

    @Column(name = "bosse_sero_sanguine", nullable = true)
    private Boolean bosseSeroSanguine ;

    @Column(name = "cephalhematome", nullable = true)
    private Boolean cephalhematome;

    @Column(name = "paralysie_obstetricale_du_plexus_brachial", nullable = true)
    private Boolean paralysieObstetricaleDuPlexusBrachial;

    @Column(name = "paralysie_faciale", nullable = true)
    private Boolean paralysieFaciale;

    @Column(name = "fracture_clavicule", nullable = true)
    private Boolean fractureClavicule;

    @Column(name = "dysplasie_hanche", nullable = true)
    private Boolean dysplasieHanche;

    @Column(name = "plagiocephalie", nullable = true)
    private Boolean plagiocephalie;

    @Column(name = "torticolis", nullable = true)
    private Boolean torticolis;

    @Column(name = "reflux_gastro_oesophagien", nullable = true)
    private Boolean refluxGastroOesophagien;

    @Column(name = "coliques", nullable = true)
    private Boolean coliques;

    @Column(name = "allaitement_maternelle", nullable = true)
    private Boolean allaitementMaternelle;

    @Column(name = "efficacite_succion", nullable = true)
    private Integer efficaciteSuccion;

    @Column(name = "sucage_pouce", nullable = true)
    private Boolean sucagePouce;

    @Column(name = "tetine", nullable = true)
    private Boolean tetine;

    @Column(name = "type_respiration", nullable = true,length=100)
    private String typeRespiration ;


    @Column(name = "presence_bruits_articulaires", nullable = true)
    private Boolean presenceBruitsArticulaires;

    @Column(name = "tics", nullable = true)
    private Boolean tics;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;

}
