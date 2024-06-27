package org.gestion_patient.security;
import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.AppUserDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.service.AppUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
public class AppUserDetailService implements UserDetailsService {

    private AppUserService appUserService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        try {
            //Charger l'utilisateur lié à cet email
            AppUserDto appUserDto = appUserService.loadByEmail(email);
            if (appUserDto == null) {
                throw new ResourceNotFoundException("no appUser found with "+email);
            }
            String decryptedEmail = appUserDto.getEmail();
            String password = appUserDto.getPassword();
            int id = appUserDto.getIdAppUser();

            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(appUserDto.getNomRole()));
            return new PraticienDetails(
                    id,
                    decryptedEmail,
                    password,
                    authorities
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}


