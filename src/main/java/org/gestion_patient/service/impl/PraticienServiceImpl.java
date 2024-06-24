package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.*;
import org.gestion_patient.entityDto.PraticienDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.exception.RessourceAlreadyexistsException;
import org.gestion_patient.mapper.PraticienMapper;
import org.gestion_patient.repository.*;
import org.gestion_patient.service.PraticienService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@AllArgsConstructor
public class PraticienServiceImpl implements PraticienService {

    private PraticienRepository praticienRepository;
    private RoleRepository roleRepository;
    private LieuRepository lieuRepository;
    private InfosprofessionnelleRepository infosprofessionnelleRepository;
    private PersonneRepository personneRepository;
    private PasswordEncoder passwordEncoder;






    @Override
    public List<PraticienDto> findAll() {
        List<Praticien> praticiens=praticienRepository.findAll();
        return praticiens.stream().map(praticien-> {
            try {
                return PraticienMapper.mapToPraticienConnecteDto(praticien);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();

    }

    @Override
    public PraticienDto findById(int id) throws Exception {
        Praticien praticienconnecte=praticienRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Praticien not found with given id: " + id));
        return PraticienMapper.mapToPraticienConnecteDto(praticienconnecte);

    }

    @Override
    public PraticienDto create(PraticienDto praticienDto) throws Exception {
        //Verification si personne déjà enregistrée (par son email), si oui leve une exception.(email saisi crypté avant vérification,car email crypté ds base de données)
        Praticien praticienToSave = praticienRepository.findByIdentiteEmail(Crypto.cryptService(praticienDto.getEmail()));
        if(praticienToSave!=null) {
            throw new RessourceAlreadyexistsException ("Praticien already exist with this email");}
        else{
            //Verification si Info societe du praticien existe , si oui leve une exception.(Cryptage des données Dto avant comparasion, car données cryptées dans la base)
            Infosprofessionnelles infos = infosprofessionnelleRepository.findByNumAdeliAndNumSiret(Crypto.cryptService(praticienDto.getNumAdeli()),Crypto.cryptService(praticienDto.getNumSiret()));
            if(infos!=null){ throw new RessourceAlreadyexistsException ("Info Pro already exist");}
            else{
                //Si pas d'exception, creation des infos professionnelles et de la personne (Cryptage des données avant persistence, car pas de mapper
                Personne personneIdNewPraticien = new Personne();
                personneIdNewPraticien.setNom(Crypto.cryptService(praticienDto.getNomPraticienConnecte().toUpperCase()));
                personneIdNewPraticien.setPrenom(Crypto.cryptService(praticienDto.getPrenomPraticienConnecte().toUpperCase()));
                personneIdNewPraticien.setEmail(Crypto.cryptService(praticienDto.getEmail()));
                personneIdNewPraticien.setTel(Crypto.cryptService(praticienDto.getTel()));
                    personneRepository.save(personneIdNewPraticien);

                Infosprofessionnelles infosprofessionnelles = new Infosprofessionnelles();
                infosprofessionnelles.setNumAdeli(Crypto.cryptService(praticienDto.getNumAdeli()));
                infosprofessionnelles.setNumSiret(Crypto.cryptService(praticienDto.getNumSiret()));
                Infosprofessionnelles infosprofessionnellesToSave = infosprofessionnelleRepository.save(infosprofessionnelles);

                //Hashage du Mot de passe
                praticienToSave.setPassword(passwordEncoder.encode(praticienDto.getPassword()));

                //Lieu sera récupéré dans le front  si non, sera créé
                Lieu lieu = lieuRepository.findByNomVilleAndCodePostal(praticienDto.getNomVille().toUpperCase(),praticienDto.getCodePostal());
                if(lieu==null){
                    lieu = new Lieu();
                    lieu.setNomVille(praticienDto.getNomVille().toUpperCase());
                    lieu.setCodePostal(praticienDto.getCodePostal());
                    lieuRepository.save(lieu);}
                //Role sera récupéré dans me front (radio box -> User ou admin)
                Role role=roleRepository.findByNomRole(praticienDto.getNomRole());
                //Persistence du nouveau praticien avec les infos ci dessus
                praticienToSave = PraticienMapper.mapToPraticienConnecte(praticienDto,role,lieu,infosprofessionnellesToSave,personneIdNewPraticien);

                return PraticienMapper.mapToPraticienConnecteDto(praticienRepository.save(praticienToSave));}}
    }

    @Override
    public PraticienDto update(int id, PraticienDto praticienDto) throws Exception {
        Praticien praticien = praticienRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Praticien not found with given id: " + id));
        //Nouveau Password mis à jour si modifié
        if(praticienDto.getPassword()!=null){praticien.setPassword(passwordEncoder.encode(praticienDto.getPassword()));}
        //Nouvelle ville mise à jour si besoin. Si pas ds base, sera créee
        Lieu lieu;
        if(praticienDto.getCodePostal()!=null && praticienDto.getNomVille()!=null){
            lieu = lieuRepository.findByNomVilleAndCodePostal(praticienDto.getNomVille().toUpperCase(),praticienDto.getCodePostal());
            if(lieu==null){
                lieu = new Lieu();
                lieu.setNomVille(praticienDto.getNomVille().toUpperCase());
                lieu.setCodePostal(praticienDto.getCodePostal());
                lieuRepository.save(lieu);}
            praticien.setVille(lieu);}

        //Recuperation des infos Personnes, et mise à jour si besoin
        Personne personne= praticien.getIdentite();
        if(praticienDto.getNomPraticienConnecte()!=null){
            personne.setNom(Crypto.cryptService(praticienDto.getNomPraticienConnecte()));}
        if(praticienDto.getPrenomPraticienConnecte()!=null){
            personne.setPrenom(Crypto.cryptService(praticienDto.getPrenomPraticienConnecte()));}
        if(praticienDto.getTel()!=null){
            personne.setTel(Crypto.cryptService(praticienDto.getTel()));}
        personneRepository.save(personne);
        praticien.setIdentite(personne);

        //Recuperation des informations professionnelles, et mise à jour si besoin
        Infosprofessionnelles infoPraticien = praticien.getInfosProfessionnelles();
        if(praticienDto.getNumAdeli()!=null && praticienDto.getNumSiret()!=null){
            Infosprofessionnelles infostoUpdate=infosprofessionnelleRepository.findByNumAdeliAndNumSiret(praticienDto.getNumAdeli(),praticienDto.getNumSiret());
            if(infostoUpdate!=null){throw new RessourceAlreadyexistsException("infos déjà existente");}
            else{

                infoPraticien.setNumAdeli(Crypto.cryptService(praticienDto.getNumAdeli()));
                infoPraticien.setNumSiret(Crypto.cryptService(praticienDto.getNumSiret()));
                infosprofessionnelleRepository.save(infoPraticien);
                praticien.setInfosProfessionnelles(infoPraticien);}
        }

        //Persisitence du praticien mis à jour
        return PraticienMapper.mapToPraticienConnecteDto(praticienRepository.save(praticien));
    }

    @Override
    @Transactional
    //Pour éviter le problème de LazyInitializationException.indique que vous essayez d'accéder à une propriété ou une collection d'une entité qui est chargée de manière paresseuse (lazy-loaded), mais que la session Hibernate n'est plus ouverte pour effectuer le chargement. Cette exception se produit souvent dans les applications qui utilisent la couche de persistance Hibernate/JPA et peut être particulièrement courante lors de l'utilisation de transactions ou de l'accès à des entités en dehors de leur contexte de session.
    public PraticienDto loadByEmail(String email) throws Exception {
        Praticien praticien =  praticienRepository.findByIdentiteEmail(Crypto.cryptService(email));
        if(praticien!=null){return PraticienMapper.mapToPraticienConnecteDto(praticien);}
        else {throw new ResourceNotFoundException("not found with this email "+email);}
    }







}