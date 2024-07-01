package org.gestion_patient.service.impl;

import lombok.AllArgsConstructor;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.*;
import org.gestion_patient.entityDto.AppUserDto;
import org.gestion_patient.entityDto.ChangePassword;
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

    private final PatientRepository patientRepository;
    // Déclarations des repositories et du password encoder
    private AppUserRepository appUserRepository;
    private RoleRepository roleRepository;
    private LieuRepository lieuRepository;
    private PersonneRepository personneRepository;
    private PasswordEncoder passwordEncoder;

    // Récupère tous les utilisateurs et les retourne sous forme de DTO
    @Override
    public List<AppUserDto> findAll() {
        List<AppUser> appUsers = appUserRepository.findAll();
        return appUsers.stream().map(appUser -> {
            try {
                return AppUserMapper.mapToAppUserDto(appUser);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    // Récupère un utilisateur par son ID et le retourne sous forme de DTO
    @Override
    public AppUserDto findById(int id) throws Exception {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AppUser not found with given id: " + id));
        return AppUserMapper.mapToAppUserDto(appUser);
    }

    // Crée un nouvel utilisateur après vérifications
    @Override
    public AppUserDto create(AppUserDto appUserDto) throws Exception {
        // Vérifie si un utilisateur avec cet email existe déjà
        AppUser appUserToSave = appUserRepository.findByIdentiteEmail(Crypto.cryptService(appUserDto.getEmail()));
        if (appUserToSave != null) {
            throw new RessourceAlreadyexistsException("AppUser already exist with this email");
        } else {
            // Vérifie si un utilisateur avec ce numéro ADELI existe déjà
            appUserToSave = appUserRepository.findByNumAdeli(Crypto.cryptService(appUserDto.getNumAdeli()));
            if (appUserToSave != null) {
                throw new RessourceAlreadyexistsException("Info Pro already exist with another user");
            } else {
                // Crée une nouvelle personne et crypte ses informations
                Personne personneIdNewPraticien = new Personne();
                personneIdNewPraticien.setNom(Crypto.cryptService(appUserDto.getNomAppUser().toUpperCase()));
                personneIdNewPraticien.setPrenom(Crypto.cryptService(appUserDto.getPrenomAppUser().toUpperCase()));
                personneIdNewPraticien.setEmail(Crypto.cryptService(appUserDto.getEmail()));
                personneIdNewPraticien.setTel(Crypto.cryptService(appUserDto.getTel()));
                personneRepository.save(personneIdNewPraticien);

                // Hash le mot de passe
                appUserDto.setPassword(passwordEncoder.encode(appUserDto.getPassword()));

                // Récupère ou crée un lieu
                Lieu lieu = lieuRepository.findByNomVilleAndCodePostal(appUserDto.getNomVille().toUpperCase(), appUserDto.getCodePostal());
                if (lieu == null) {
                    lieu = new Lieu();
                    lieu.setNomVille(appUserDto.getNomVille().toUpperCase());
                    lieu.setCodePostal(appUserDto.getCodePostal());
                    lieuRepository.save(lieu);
                }

                // Récupère le rôle
                Role role = roleRepository.findByNomRole(appUserDto.getNomRole());

                appUserDto.setActive(true);

                // Map les informations et sauvegarde l'utilisateur
                appUserToSave = AppUserMapper.mapToAppUser(appUserDto, role, lieu, personneIdNewPraticien);
                return AppUserMapper.mapToAppUserDto(appUserRepository.save(appUserToSave));
            }
        }
    }

    // Met à jour un utilisateur existant
    @Override
    public AppUserDto update(int id, AppUserDto appUserDto) throws Exception {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Praticien not found with given id: " + id));

        // Met à jour le lieu si nécessaire
        Lieu lieu;
        if (appUserDto.getCodePostal() != null && appUserDto.getNomVille() != null
                && !appUserDto.getNomVille().equals(appUser.getVille().getNomVille())) {
            lieu = lieuRepository.findByNomVilleAndCodePostal(appUserDto.getNomVille().toUpperCase(), appUserDto.getCodePostal());
            if (lieu == null) {
                lieu = new Lieu();
                lieu.setNomVille(appUserDto.getNomVille().toUpperCase());
                lieu.setCodePostal(appUserDto.getCodePostal());
                lieuRepository.save(lieu);
            }
            appUser.setVille(lieu);
        }

        // Met à jour les informations de la personne
        Personne personne = appUser.getIdentite();
        if (appUserDto.getNomAppUser() != null) {
            personne.setNom(Crypto.cryptService(appUserDto.getNomAppUser()));
        }
        if (appUserDto.getPrenomAppUser() != null) {
            personne.setPrenom(Crypto.cryptService(appUserDto.getPrenomAppUser()));
        }
        if (appUserDto.getTel() != null) {
            personne.setTel(Crypto.cryptService(appUserDto.getTel()));
        }
        personneRepository.save(personne);
        appUser.setIdentite(personne);

        // Met à jour le numéro ADELI si nécessaire
        if (appUserDto.getNumAdeli() != null && !Crypto.cryptService(appUserDto.getNumAdeli()).equals(appUser.getNumAdeli())) {
            AppUser appUserDouble = appUserRepository.findByNumAdeli(Crypto.cryptService(appUserDto.getNumAdeli()));
            if (appUserDouble != null) {
                throw new RessourceAlreadyexistsException("AppUser already exists with this numero Adeli");
            } else {
                appUser.setNumAdeli(Crypto.cryptService(appUserDto.getNumAdeli()));
            }
        }

        // Sauvegarde les modifications et retourne l'utilisateur mis à jour sous forme de DTO
        return AppUserMapper.mapToAppUserDto(appUserRepository.save(appUser));
    }

    // Charge un utilisateur par son email et le retourne sous forme de DTO
    @Override
    @Transactional // Assure la gestion transactionnelle pour éviter les problèmes de LazyInitializationException
    public AppUserDto loadByEmail(String email) throws Exception {
        AppUser appUser = appUserRepository.findByIdentiteEmail(Crypto.cryptService(email));
        if (appUser != null) {
            return AppUserMapper.mapToAppUserDto(appUser);
        } else {
            throw new ResourceNotFoundException("not found with this email " + email);
        }
    }

    // Met à jour le mot de passe d'un utilisateur
    @Override
    public void updatePassword(ChangePassword changePassword, int id) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AppUser not found with given id: " + id));

        // Vérifie que l'ancien mot de passe correspond
        if (!passwordEncoder.matches(changePassword.getOldPassword(), appUser.getPassword())) {
            throw new ResourceNotFoundException("Mauvais mot de passe");
        } else {
            // Encode et met à jour le nouveau mot de passe
            appUser.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
            appUserRepository.save(appUser);
        }
    }

    @Override
    public void delete(int id) {
        AppUser appUserToDelete = appUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("AppUser not found with given id: " + id));
        //Suppression si aucun patient associé :
        List<Patient> patientList = patientRepository.findAllByAppUser(appUserToDelete);
        if(patientList.isEmpty()){appUserRepository.delete(appUserToDelete);}
    }
}
