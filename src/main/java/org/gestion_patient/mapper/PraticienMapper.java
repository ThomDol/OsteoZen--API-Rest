package org.gestion_patient.mapper;

import org.gestion_patient.Data.DataUtil;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.*;
import org.gestion_patient.entityDto.PraticienDto;

public class PraticienMapper {
    public static PraticienDto mapToPraticienConnecteDto (Praticien praticien) throws Exception {


        return new PraticienDto(
                praticien.getIdPraticien(),
                praticien.getPassword(),
                praticien.getRole().getNomRole(),
                praticien.getVille().getNomVille(),
                praticien.getVille().getCodePostal(),
                Crypto.decryptService(praticien.getInfosProfessionnelles().getNumAdeli()),
                Crypto.decryptService(praticien.getInfosProfessionnelles().getNumSiret()),
                Crypto.decryptService(praticien.getIdentite().getNom()),
                Crypto.decryptService(praticien.getIdentite().getPrenom()),
                Crypto.decryptService(praticien.getIdentite().getEmail()),
                DataUtil.displayStringDecrypt(praticien.getIdentite().getTel())

        );
    }

    public static Praticien mapToPraticienConnecte (PraticienDto praticienDto, Role role, Lieu lieu, Infosprofessionnelles infosprofessionnelles, Personne personne) throws Exception {
        Praticien praticienconnecte = new Praticien();
        praticienconnecte.setIdPraticien(praticienDto.getIdPraticien());
        praticienconnecte.setPassword(praticienDto.getPassword());
        praticienconnecte.setRole(role);
        praticienconnecte.setVille(lieu);
        praticienconnecte.setInfosProfessionnelles(infosprofessionnelles);
        praticienconnecte.setIdentite(personne);
        return praticienconnecte;
    }

}