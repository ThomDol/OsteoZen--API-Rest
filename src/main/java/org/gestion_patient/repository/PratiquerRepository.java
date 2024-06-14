package org.gestion_patient.repository;

import org.gestion_patient.entity.Pratiquer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PratiquerRepository extends JpaRepository<Pratiquer,Integer> {
    Pratiquer findBySportIdSportAndPatientIdPatient(int idSport, int patientId);
    List<Pratiquer> findAllByPatientIdPatient(int idPatient);
}
