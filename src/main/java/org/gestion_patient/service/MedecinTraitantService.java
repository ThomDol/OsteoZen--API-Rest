package org.gestion_patient.service;

import org.gestion_patient.entityDto.MedecintraitantDto;

import java.util.List;

public interface MedecinTraitantService {
  List<MedecintraitantDto> getAll ();
  MedecintraitantDto findMedecintraitantById(int id) throws Exception;
  MedecintraitantDto createMedecintraitant(MedecintraitantDto medecintraitantDto) throws Exception;
  MedecintraitantDto updateMedecinTraintant(MedecintraitantDto medecintraitantDto , int id) throws Exception;
}
