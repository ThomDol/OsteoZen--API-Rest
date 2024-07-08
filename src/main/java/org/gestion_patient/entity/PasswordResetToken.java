package org.gestion_patient.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetToken {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String token;
        private Date expiryDate;

        @OneToOne(targetEntity = AppUser.class, fetch = FetchType.EAGER)
        @JoinColumn(nullable = false, name = "id_appuser")
        private AppUser appUser;

    }




