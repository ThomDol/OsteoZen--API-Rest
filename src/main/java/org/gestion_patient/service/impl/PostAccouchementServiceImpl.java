package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.entity.Accouchement;
import org.gestion_patient.entity.PostAccouchement;
import org.gestion_patient.entityDto.PostAccouchementDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.mapper.PostAccouchementMapper;
import org.gestion_patient.repository.AccouchementRepository;
import org.gestion_patient.repository.PostAccouchementRepository;
import org.gestion_patient.service.PostAccouchementService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostAccouchementServiceImpl implements PostAccouchementService {
    private PostAccouchementRepository postAccouchementRepository;
    private AccouchementRepository accouchementRepository;

    @Override
    public PostAccouchementDto createByAccouchement(PostAccouchementDto postAccouchementDto, int idAccouchement) {
        Accouchement accouchement = accouchementRepository.findById(idAccouchement).orElseThrow(()->new ResourceNotFoundException("Accouchement with id"+idAccouchement+" doesn't exist"));
        PostAccouchement postAccouchement = PostAccouchementMapper.mapToPostAccouchement(postAccouchementDto,accouchement);
        PostAccouchement postAccouchementSaved = postAccouchementRepository.save(postAccouchement);
        accouchement.setPostAccouchement(postAccouchementSaved);
        accouchementRepository.save(accouchement);
        return PostAccouchementMapper.mapToPostAccouchementDto(postAccouchementSaved);
    }

    @Override
    public PostAccouchementDto update(int idToUpdate, PostAccouchementDto postAccouchementDtoUpdated) {
        PostAccouchement postAccouchementToUpdate = postAccouchementRepository.findById(idToUpdate).orElseThrow(()->new ResourceNotFoundException("PostAccouchement with id"+idToUpdate+" doesn't exist"));
        if(postAccouchementDtoUpdated.getQualiteSommeil()!=null){postAccouchementToUpdate.setQualiteSommeil(postAccouchementDtoUpdated.getQualiteSommeil());}
        if(postAccouchementDtoUpdated.getInstabiliteVesicale()!=null){postAccouchementToUpdate.setInstabiliteVesicale(postAccouchementDtoUpdated.getInstabiliteVesicale());}
        if(postAccouchementDtoUpdated.getEcoulementsVaginaux()!=null){postAccouchementToUpdate.setEcoulementsVaginaux(postAccouchementDtoUpdated.getEcoulementsVaginaux());}
        if(postAccouchementDtoUpdated.getRetourDeCouche()!=null){postAccouchementToUpdate.setRetourDeCouche(postAccouchementDtoUpdated.getRetourDeCouche());}
        if(postAccouchementDtoUpdated.getDouleurAbdominales()!=null){postAccouchementToUpdate.setDouleurAbdominales(postAccouchementDtoUpdated.getDouleurAbdominales());}
        if(postAccouchementDtoUpdated.getFievre()!=null){postAccouchementToUpdate.setFievre(postAccouchementDtoUpdated.getFievre());}
        if(postAccouchementDtoUpdated.getInfosComplementaires()!=null){postAccouchementToUpdate.setInfosComplementaires(postAccouchementDtoUpdated.getInfosComplementaires());}

        PostAccouchement postAccouchementUpdated = postAccouchementRepository.save(postAccouchementToUpdate);

        return PostAccouchementMapper.mapToPostAccouchementDto(postAccouchementUpdated);
    }

    @Override
    public PostAccouchementDto getByIdAccouchement(int id) {
        PostAccouchement postAccouchement = postAccouchementRepository.findByAccouchementIdAccouchement(id);
        if(postAccouchement!=null){
            return PostAccouchementMapper.mapToPostAccouchementDto(postAccouchement);
        }
        else{return null;}
    }

    @Override
    public PostAccouchementDto getById(int id) {
        PostAccouchement postAccouchement = postAccouchementRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("PostAccouchement with id"+id+" doesn't exist"));
        return   PostAccouchementMapper.mapToPostAccouchementDto(postAccouchement);
    }

    @Override
    public void delete(int id) {
        PostAccouchement postAccouchementToDelete = postAccouchementRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("PostAccouchement with id"+id+" doesn't exist"));
        // Récupérer l'Accouchement associé
        Accouchement associatedAccouchement = postAccouchementToDelete.getAccouchement();
        if (associatedAccouchement != null) {
            // Dissocier le PostAccouchement de l'Accouchement
            associatedAccouchement.setPostAccouchement(null);
            // Sauvegarder l'Accouchement mis à jour
            accouchementRepository.save(associatedAccouchement);
        }

        // Supprimer l'entrée PostAccouchement
        postAccouchementRepository.delete(postAccouchementToDelete);
    }
}
