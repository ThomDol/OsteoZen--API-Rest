package org.gestion_patient.controller;

import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.AppUserDto;
import org.gestion_patient.entityDto.ChangePassword;
import org.gestion_patient.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/api")
public class AppUserController {
    private AppUserService appUserService;

    @GetMapping(path="/profile")
    public ResponseEntity<AppUserDto> profile(Principal principal) throws Exception {
        AppUserDto appUserDto =  appUserService.loadByEmail(principal.getName());
        return new ResponseEntity<>(appUserDto,HttpStatus.CREATED);
    }

    @PostMapping("/praticien")
    public ResponseEntity<AppUserDto> createNewAppUser(@RequestBody AppUserDto appUserDto) throws Exception {
        AppUserDto appUserDtoSaved = appUserService.create(appUserDto);
        return new ResponseEntity<>(appUserDtoSaved, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/praticien/all")
    public ResponseEntity<List<AppUserDto>> getAlAppUser(){
        List<AppUserDto> appUsers = appUserService.findAll();
        return new ResponseEntity<>(appUsers,HttpStatus.OK);
    }

    @GetMapping("/praticien/{id}")
    public ResponseEntity<AppUserDto> getAppUserById(@PathVariable int id) throws Exception {
        AppUserDto appUserDto = appUserService.findById(id);
        return new ResponseEntity<>(appUserDto,HttpStatus.OK);
    }

    @PutMapping("/praticien/{id}")
    public ResponseEntity<AppUserDto> updateAppUser(@PathVariable ("id") int id, @RequestBody AppUserDto appUserDto) throws Exception {
        AppUserDto updatedPraticien = appUserService.update(id,appUserDto);
        return new ResponseEntity<>(updatedPraticien,HttpStatus.OK);
    }

    @DeleteMapping("/praticien/{id}")
    public ResponseEntity<String> deleteAppUser (@PathVariable ("id") int id){
        appUserService.delete(id);
        return new ResponseEntity<>("deleted with success",HttpStatus.OK);
    }

    @PostMapping("/praticien/updateMdp/{id}")
    public ResponseEntity<String> upadtePassword(@PathVariable int id, @RequestBody ChangePassword changePassword){
        appUserService.updatePassword(changePassword,id);
        return new ResponseEntity<>("updated with success",HttpStatus.OK);
    }



}