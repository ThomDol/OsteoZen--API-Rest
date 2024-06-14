package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.Lieu;
import org.gestion_patient.entity.Medecintraitant;
import org.gestion_patient.entity.Personne;
import org.gestion_patient.entityDto.MedecintraitantDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.exception.RessourceAlreadyexistsException;
import org.gestion_patient.mapper.MedecinTraitantMapper;
import org.gestion_patient.repository.LieuRepository;
import org.gestion_patient.repository.MedecintraitantRepository;
import org.gestion_patient.repository.PersonneRepository;
import org.gestion_patient.service.MedecinTraitantService;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MedecinTraitantImpl implements MedecinTraitantService {
    private final MedecintraitantRepository medecintraitantRepository;
    private final PersonneRepository personneRepository;
    private final LieuRepository lieuRepository;

    //Comme les infos nom et prenom medecin traitant sont cryptées, et que le cryptage se passe aussi ds le Mapper, il faut lever une exception à chaque utilisation du Mapper ds les fonctions

    @Override
    public List<MedecintraitantDto> getAll() {
        List<Medecintraitant> medecins = medecintraitantRepository.findAll();
        return medecins.stream().map(medecin-> {
            try {
                return MedecinTraitantMapper.mapToMedecinTraitantDto(medecin);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }
    //Decryptage pour affichage via le Mapper
    @Override
    public MedecintraitantDto findMedecintraitantById(int id) throws Exception {
        Medecintraitant medecinTraitant = medecintraitantRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Medecin not found with given id " + id));
        return MedecinTraitantMapper.mapToMedecinTraitantDto(medecinTraitant);
    }

    //Cryptage via le Mapper
    @Override
    public MedecintraitantDto createMedecintraitant(MedecintraitantDto medecintraitantDto) throws Exception {
        Medecintraitant medecintraitantToSave = medecintraitantRepository.findByIdentiteDocNomAndIdentiteDocPrenomAndLieuNomVille(Crypto.cryptService(medecintraitantDto.getNomMedecinTraitant()), Crypto.cryptService(medecintraitantDto.getPrenomMedecinTraitant()),medecintraitantDto.getVille());
        if(medecintraitantToSave!=null){
            throw new RessourceAlreadyexistsException("This Medecintraitant alredy exists");
        }
        //Verification si homonyme existe ds une autre ville, si non crée l'identité, si oui reutilise la même
        Personne personne = personneRepository.findByNomAndPrenom(Crypto.cryptService(medecintraitantDto.getNomMedecinTraitant()),Crypto.cryptService(medecintraitantDto.getPrenomMedecinTraitant()));
        if(personne==null){
            personne = new Personne();
            personne.setNom(Crypto.cryptService(medecintraitantDto.getNomMedecinTraitant()));
            personne.setPrenom(Crypto.cryptService(medecintraitantDto.getPrenomMedecinTraitant()));
            personne=personneRepository.save(personne);}
        //Lieu récupéré dans le front et crée avant si n'existe pas ds la bdd
        Lieu lieu = lieuRepository.findByNomVille(medecintraitantDto.getVille());
        Medecintraitant medecintraitant = MedecinTraitantMapper.mapToMedecinTraitant(medecintraitantDto,personne,lieu);
        Medecintraitant medecintraitantSaved = medecintraitantRepository.save(medecintraitant);
        return MedecinTraitantMapper.mapToMedecinTraitantDto(medecintraitantSaved);
    }

    @Override
    public MedecintraitantDto updateMedecinTraintant(MedecintraitantDto medecintraitantDto , int id ) throws Exception {
        Medecintraitant medecintraitant =  medecintraitantRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Medecin not found with given id " + id));
        Personne personneToUpdatde =   medecintraitant.getIdentiteDoc();
        personneToUpdatde.setNom(Crypto.cryptService(medecintraitantDto.getNomMedecinTraitant()));
        personneToUpdatde.setPrenom(Crypto.cryptService(medecintraitantDto.getPrenomMedecinTraitant()));
        personneToUpdatde = personneRepository.save(personneToUpdatde);
        medecintraitant.setIdentiteDoc(personneToUpdatde);
        medecintraitant=medecintraitantRepository.save(medecintraitant);
        return MedecinTraitantMapper.mapToMedecinTraitantDto(medecintraitant);

    }
}