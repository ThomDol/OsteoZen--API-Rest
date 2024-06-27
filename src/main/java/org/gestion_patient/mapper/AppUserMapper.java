package org.gestion_patient.mapper;

import org.gestion_patient.Data.DataUtil;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.*;
import org.gestion_patient.entityDto.AppUserDto;

public class AppUserMapper {
    public static AppUserDto mapToAppUserDto (AppUser appUser) throws Exception {


        return new AppUserDto(
                appUser.getIdAppUser(),
                appUser.getPassword(),
                appUser.getRole().getNomRole(),
                appUser.getVille().getNomVille(),
                appUser.getVille().getCodePostal(),
                Crypto.decryptService(appUser.getNumAdeli()),
                Crypto.decryptService(appUser.getIdentite().getNom()),
                Crypto.decryptService(appUser.getIdentite().getPrenom()),
                Crypto.decryptService(appUser.getIdentite().getEmail()),
                DataUtil.displayStringDecrypt(appUser.getIdentite().getTel())

        );
    }

    public static AppUser mapToAppUser (AppUserDto appUserDto, Role role, Lieu lieu, Personne personne) throws Exception {
        AppUser appUser = new AppUser();
        appUser.setIdAppUser(appUserDto.getIdAppUser());
        appUser.setPassword(appUserDto.getPassword());
        appUser.setRole(role);
        appUser.setVille(lieu);
        appUser.setNumAdeli(Crypto.cryptService(appUserDto.getNumAdeli()));
        appUser.setIdentite(personne);
        return appUser;
    }

}