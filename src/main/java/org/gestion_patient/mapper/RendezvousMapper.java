package org.gestion_patient.mapper;

import org.gestion_patient.Data.DataUtil;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.Patient;
import org.gestion_patient.entity.Rendezvous;
import org.gestion_patient.entityDto.RendezvousDto;

public class RendezvousMapper {
    public static RendezvousDto mapToRendezvousDto (Rendezvous rendezvous) throws Exception {
        return new RendezvousDto(
                rendezvous.getIdRendezVous(),
                rendezvous.getDateRendeVous(),
                DataUtil.displayStringDecrypt(rendezvous.getSyntheseRendezVous()),
                DataUtil.displayString(rendezvous.getSchemaRendezVous()),
                DataUtil.displayString(rendezvous.getNomFacture()),
                DataUtil.displayString(rendezvous.getNomCourrier()),
                rendezvous.getPatient().getIdPatient()
        );
    }

    public static Rendezvous maptoRendezvous(RendezvousDto rendezvousDto, Patient patient) throws Exception {
        Rendezvous rendezvous = new Rendezvous();
        rendezvous.setIdRendezVous(rendezvousDto.getIdRendezVous());
        rendezvous.setDateRendeVous(rendezvousDto.getDateRendeVous());
        if (rendezvousDto.getSyntheseRendezVous() != null) {
            rendezvous.setSyntheseRendezVous(Crypto.cryptService(rendezvousDto.getSyntheseRendezVous()));}
        if (rendezvousDto.getSchemaRendezVous() != null) {rendezvous.setSchemaRendezVous(rendezvousDto.getSchemaRendezVous());}
        if (rendezvousDto.getNomFacture() != null) {rendezvous.setNomFacture(rendezvousDto.getNomFacture());}
        if (rendezvousDto.getNomCourrier() != null) {
            rendezvous.setNomCourrier(rendezvousDto.getNomCourrier());}
        rendezvous.setPatient(patient);
        return rendezvous;
    }

}
