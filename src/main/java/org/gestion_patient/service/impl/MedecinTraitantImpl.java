package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.Lieu;
import org.gestion_patient.entity.Medecintraitant;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entity.Personne;
import org.gestion_patient.entityDto.MedecintraitantDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.exception.RessourceAlreadyexistsException;
import org.gestion_patient.mapper.MedecinTraitantMapper;
import org.gestion_patient.repository.LieuRepository;
import org.gestion_patient.repository.MedecintraitantRepository;
import org.gestion_patient.repository.PatientRepository;
import org.gestion_patient.repository.PersonneRepository;
import org.gestion_patient.service.MedecinTraitantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class MedecinTraitantImpl implements MedecinTraitantService {
    private final MedecintraitantRepository medecintraitantRepository;
    private final PersonneRepository personneRepository;
    private final LieuRepository lieuRepository;
    private final PatientRepository patientRepository;

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
        // Vérification si le médecin existe déjà par nom, prénom, ville et code postal
        Medecintraitant medecintraitantToSave = medecintraitantRepository.findByIdentiteDocNomAndIdentiteDocPrenomAndLieuNomVilleAndLieuCodePostal(
                Crypto.cryptService(medecintraitantDto.getNomMedecinTraitant()),
                Crypto.cryptService(medecintraitantDto.getPrenomMedecinTraitant()),
                medecintraitantDto.getVilleMedecinTraitant(),
                medecintraitantDto.getCodePostalMedecinTraitant()
        );

        if (medecintraitantToSave != null) {
            throw new RessourceAlreadyexistsException("This Medecintraitant already exists");
        }

        // Vérification de l'homonyme dans une autre ville
        Personne personne = personneRepository.findByNomAndPrenom(
                Crypto.cryptService(medecintraitantDto.getNomMedecinTraitant()),
                Crypto.cryptService(medecintraitantDto.getPrenomMedecinTraitant())
        );

        // Si une personne est trouvée et qu'elle a un email ou un téléphone, elle ne peut pas être réutilisée
        if (personne != null && (personne.getTel() != null || personne.getEmail() != null)) {
            personne = null;
        }

        // Si aucune personne n'est trouvée, créer une nouvelle entrée
        if (personne == null) {
            personne = new Personne();
            personne.setNom(Crypto.cryptService(medecintraitantDto.getNomMedecinTraitant().toUpperCase()));
            personne.setPrenom(Crypto.cryptService(medecintraitantDto.getPrenomMedecinTraitant().toUpperCase()));
            personne = personneRepository.save(personne);
        }

        // Vérification et création du lieu si nécessaire
        Lieu lieu = lieuRepository.findByNomVilleAndCodePostal(
                medecintraitantDto.getVilleMedecinTraitant().toUpperCase(),
                medecintraitantDto.getCodePostalMedecinTraitant()
        );

        if (lieu == null) {
            lieu = new Lieu();
            lieu.setNomVille(medecintraitantDto.getVilleMedecinTraitant().toUpperCase());
            lieu.setCodePostal(medecintraitantDto.getCodePostalMedecinTraitant());
            lieu = lieuRepository.save(lieu);
        }

        // Création de l'entité Medecintraitant
        Medecintraitant medecintraitant = MedecinTraitantMapper.mapToMedecinTraitant(medecintraitantDto, personne, lieu);
        Medecintraitant medecintraitantSaved = medecintraitantRepository.save(medecintraitant);

        // Retourner le DTO du médecin traitant enregistré
        return MedecinTraitantMapper.mapToMedecinTraitantDto(medecintraitantSaved);
    }

    @Transactional
    @Override
    public void delete(int id) {
        Medecintraitant docToDelete = medecintraitantRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Medecin not found"));
        //Verification si ce medecin n'est pas associé à des patients ds la base de données. Si oui suppression impossible
        List<Patient> patientList = patientRepository.findAllByMedecinTraitant(docToDelete);
        if(patientList.size()==0){
        //Verification si les nom et prenom ne sont pas utilisés pour un homonyme. Si oui, suppression impossible
        List<Medecintraitant> medecintraitantList=medecintraitantRepository.findAllByIdentiteDoc(docToDelete.getIdentiteDoc());
        if(medecintraitantList.size()==0){medecintraitantRepository.delete(docToDelete);}
        }
    }


}