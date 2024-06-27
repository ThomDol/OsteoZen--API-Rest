package org.gestion_patient.repository;

import org.gestion_patient.entity.Rendezvous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous,Integer> {
    List<Rendezvous> findAllByPatientIdPatient(int idPatient);
    void deleteAllByPatientIdPatient(int idPatient);
}
