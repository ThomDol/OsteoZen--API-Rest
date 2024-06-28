package org.gestion_patient.service;

import org.gestion_patient.entityDto.AppUserDto;
import org.gestion_patient.entityDto.ChangePassword;


import java.util.List;

public interface AppUserService {
    List<AppUserDto> findAll();
    AppUserDto findById(int id) throws Exception;
    AppUserDto create(AppUserDto appUserDto) throws Exception;
    AppUserDto update(int id, AppUserDto appUserDto) throws Exception;
    AppUserDto loadByEmail(String email) throws Exception;
    void updatePassword(ChangePassword changePassword, int id);

}
