package org.gestion_patient.repository;

import org.gestion_patient.entity.Rendezvous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous,Integer> {
    List<Rendezvous> findAllByPatientIdPatient(int idPatient);
    List<Rendezvous> findAllByPatientIdPatientAndPatientPraticienIdPraticien(int idPatient,int idPraticien);
    Rendezvous findByIdRendezVousAndPatientPraticienIdPraticien (int id,int idPraticien);
    Rendezvous findByIdRendezVousAndPatientIdPatient (int idRendezVous,int idPatient);
    void deleteAllByPatientIdPatient(int id);
}
