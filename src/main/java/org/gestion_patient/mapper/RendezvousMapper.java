package org.gestion_patient.mapper;

import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entity.Rendezvous;
import org.gestion_patient.entityDto.RendezvousDto;

public class RendezvousMapper {
    public static RendezvousDto mapToRendezvousDto (Rendezvous rendezvous) throws Exception {
        return new RendezvousDto(
                rendezvous.getIdRendezVous(),
                rendezvous.getDateRendeVous(),
                Crypto.decryptService(rendezvous.getSyntheseRendezVous()),
                rendezvous.getSchemaRendezVous(),
                rendezvous.getNomFacture(),
                rendezvous.getNomCourrier(),
                rendezvous.getPatient().getIdPatient()
        );
    }

    public static Rendezvous maptoRendezvous(RendezvousDto rendezvousDto, Patient patient) throws Exception {
        Rendezvous rendezvous = new Rendezvous();
        rendezvous.setIdRendezVous(rendezvousDto.getIdRendezVous());
        rendezvous.setDateRendeVous(rendezvousDto.getDateRendeVous());
        rendezvous.setSyntheseRendezVous(Crypto.cryptService(rendezvousDto.getSyntheseRendezVous()));
        rendezvous.setSchemaRendezVous(rendezvousDto.getSchemaRendezVous());
        rendezvous.setNomFacture(rendezvousDto.getNomFacture());
        rendezvous.setNomCourrier(rendezvousDto.getNomCourrier());
        rendezvous.setPatient(patient);
        return rendezvous;
    }

}

