package org.gestion_patient.security;
import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.PraticienDto;
import org.gestion_patient.exception.ResourceNotFoundException;
import org.gestion_patient.service.PraticienService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
public class PraticienDetailService implements UserDetailsService {

    private PraticienService praticienService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        try {
            //Charger l'utilisateur lié à cet email
            PraticienDto praticienconnecteDto = praticienService.loadByEmail(email);
            if (praticienconnecteDto == null) {
                throw new ResourceNotFoundException("no praticien found with "+email);
            }
            String decryptedEmail = praticienconnecteDto.getEmail();
            String password = praticienconnecteDto.getPassword();

            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(praticienconnecteDto.getNomRole()));
            return new User(decryptedEmail, password, authorities);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}


