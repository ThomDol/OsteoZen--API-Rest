package org.gestion_patient.service;


import org.gestion_patient.entityDto.LieuDto;

import java.util.List;

public interface LieuService {
    LieuDto createLieu(LieuDto lieuDto);
    LieuDto updateLieu(int id , LieuDto lieuDto);
    List<LieuDto> findAll();
    LieuDto getLieuById(int id);
    void  deleteLieu(int id);
}
