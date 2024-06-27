package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.*;
import org.gestion_patient.entityDto.AppUserDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.exception.RessourceAlreadyexistsException;
import org.gestion_patient.mapper.AppUserMapper;
import org.gestion_patient.repository.*;
import org.gestion_patient.service.AppUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository appUserRepository;
    private RoleRepository roleRepository;
    private LieuRepository lieuRepository;
    private InfosprofessionnelleRepository infosprofessionnelleRepository;
    private PersonneRepository personneRepository;
    private PasswordEncoder passwordEncoder;






    @Override
    public List<AppUserDto> findAll() {
        List<AppUser> appUsers = appUserRepository.findAll();
        return appUsers.stream().map(appUser-> {
            try {
                return AppUserMapper.mapToAppUserDto(appUser);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();

    }

    @Override
    public AppUserDto findById(int id) throws Exception {
        AppUser appUser= appUserRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("AppUser not found with given id: " + id));
        return AppUserMapper.mapToAppUserDto(appUser);

    }

    @Override
    public AppUserDto create(AppUserDto appUserDto) throws Exception {
        //Verification si personne déjà enregistrée (par son email), si oui leve une exception.(email saisi crypté avant vérification,car email crypté ds base de données)
        AppUser appUserToSave = appUserRepository.findByIdentiteEmail(Crypto.cryptService(appUserDto.getEmail()));
        if(appUserToSave !=null) {
            throw new RessourceAlreadyexistsException ("AppUser already exist with this email");}
        else{
            //Verification si Info societe du User existe , si oui leve une exception.(Cryptage des données Dto avant comparasion, car données cryptées dans la base)
            Infosprofessionnelles infos = infosprofessionnelleRepository.findByNumAdeliAndNumSiret(Crypto.cryptService(appUserDto.getNumAdeli()),Crypto.cryptService(appUserDto.getNumSiret()));
            if(infos!=null){ throw new RessourceAlreadyexistsException ("Info Pro already exist");}
            else{
                //Si pas d'exception, creation des infos professionnelles et de la personne (Cryptage des données avant persistence, car pas de mapper
                Personne personneIdNewPraticien = new Personne();
                personneIdNewPraticien.setNom(Crypto.cryptService(appUserDto.getNomAppUser().toUpperCase()));
                personneIdNewPraticien.setPrenom(Crypto.cryptService(appUserDto.getPrenomAppUser().toUpperCase()));
                personneIdNewPraticien.setEmail(Crypto.cryptService(appUserDto.getEmail()));
                personneIdNewPraticien.setTel(Crypto.cryptService(appUserDto.getTel()));
                    personneRepository.save(personneIdNewPraticien);

                Infosprofessionnelles infosprofessionnelles = new Infosprofessionnelles();
                infosprofessionnelles.setNumAdeli(Crypto.cryptService(appUserDto.getNumAdeli()));
                infosprofessionnelles.setNumSiret(Crypto.cryptService(appUserDto.getNumSiret()));
                Infosprofessionnelles infosprofessionnellesToSave = infosprofessionnelleRepository.save(infosprofessionnelles);

                //Hashage du Mot de passe
                appUserDto.setPassword(passwordEncoder.encode(appUserDto.getPassword()));

                //Lieu sera récupéré dans le front  si non, sera créé
                Lieu lieu = lieuRepository.findByNomVilleAndCodePostal(appUserDto.getNomVille().toUpperCase(), appUserDto.getCodePostal());
                if(lieu==null){
                    lieu = new Lieu();
                    lieu.setNomVille(appUserDto.getNomVille().toUpperCase());
                    lieu.setCodePostal(appUserDto.getCodePostal());
                    lieuRepository.save(lieu);}
                //Role sera récupéré dans me front (radio box -> User ou admin)
                Role role=roleRepository.findByNomRole(appUserDto.getNomRole());
                //Persistence du nouveau praticien avec les infos ci dessus
                appUserToSave = AppUserMapper.mapToAppUser(appUserDto,role,lieu,infosprofessionnellesToSave,personneIdNewPraticien);

                return AppUserMapper.mapToAppUserDto(appUserRepository.save(appUserToSave));}}
    }

    @Override
    public AppUserDto update(int id, AppUserDto appUserDto) throws Exception {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Praticien not found with given id: " + id));
        //Nouveau Password mis à jour si modifié
        if(appUserDto.getPassword()!=null){
            appUser.setPassword(passwordEncoder.encode(appUserDto.getPassword()));}
        //Nouvelle ville mise à jour si besoin. Si pas ds base, sera créee
        Lieu lieu;
        if(appUserDto.getCodePostal()!=null && appUserDto.getNomVille()!=null){
            lieu = lieuRepository.findByNomVilleAndCodePostal(appUserDto.getNomVille().toUpperCase(), appUserDto.getCodePostal());
            if(lieu==null){
                lieu = new Lieu();
                lieu.setNomVille(appUserDto.getNomVille().toUpperCase());
                lieu.setCodePostal(appUserDto.getCodePostal());
                lieuRepository.save(lieu);}
            appUser.setVille(lieu);}

        //Recuperation des infos Personnes, et mise à jour si besoin
        Personne personne= appUser.getIdentite();
        if(appUserDto.getNomAppUser()!=null){
            personne.setNom(Crypto.cryptService(appUserDto.getNomAppUser()));}
        if(appUserDto.getPrenomAppUser()!=null){
            personne.setPrenom(Crypto.cryptService(appUserDto.getPrenomAppUser()));}
        if(appUserDto.getTel()!=null){
            personne.setTel(Crypto.cryptService(appUserDto.getTel()));}
        personneRepository.save(personne);
        appUser.setIdentite(personne);

        //Recuperation des informations professionnelles, et mise à jour si besoin (Verification que les données mises à jour ne sont pas déjà enregistrées en base de données
        Infosprofessionnelles infoPraticien = appUser.getInfosProfessionnelles();
        if(appUserDto.getNumAdeli()!=null && appUserDto.getNumSiret()!=null){
            Infosprofessionnelles infostoUpdate=infosprofessionnelleRepository.findByNumAdeliAndNumSiret(appUserDto.getNumAdeli(), appUserDto.getNumSiret());
            if(infostoUpdate!=null){throw new RessourceAlreadyexistsException("infos déjà existentes");}
            else{

                infoPraticien.setNumAdeli(Crypto.cryptService(appUserDto.getNumAdeli()));
                infoPraticien.setNumSiret(Crypto.cryptService(appUserDto.getNumSiret()));
                infosprofessionnelleRepository.save(infoPraticien);
                appUser.setInfosProfessionnelles(infoPraticien);}
        }

        //Persisitence du praticien mis à jour
        return AppUserMapper.mapToAppUserDto(appUserRepository.save(appUser));
    }

    @Override
    @Transactional
    //Pour éviter le problème de LazyInitializationException.indique que vous essayez d'accéder à une propriété ou une collection d'une entité qui est chargée de manière paresseuse (lazy-loaded), mais que la session Hibernate n'est plus ouverte pour effectuer le chargement. Cette exception se produit souvent dans les applications qui utilisent la couche de persistance Hibernate/JPA et peut être particulièrement courante lors de l'utilisation de transactions ou de l'accès à des entités en dehors de leur contexte de session.
    public AppUserDto loadByEmail(String email) throws Exception {
        AppUser appUser =  appUserRepository.findByIdentiteEmail(Crypto.cryptService(email));
        if(appUser !=null){return AppUserMapper.mapToAppUserDto(appUser);}
        else {throw new ResourceNotFoundException("not found with this email "+email);}
    }







}