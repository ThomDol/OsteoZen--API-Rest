package org.gestion_patient.controller;


import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.PostAccouchementDto;
import org.gestion_patient.service.PostAccouchementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/postaccouchement")
public class PostAccouchementController {
    private PostAccouchementService postAccouchementService;


    @PostMapping("/{idAccouchement}")
    public ResponseEntity<PostAccouchementDto> createPostAccouchement(@RequestBody PostAccouchementDto postAccouchementDto, @PathVariable int idAccouchement ) {
        PostAccouchementDto postAccouchementDtoCreated = postAccouchementService.create(postAccouchementDto,idAccouchement);
        return new ResponseEntity<>(postAccouchementDtoCreated, HttpStatus.CREATED);
    }
    @GetMapping("{idPostAccouchement}")
    public ResponseEntity<PostAccouchementDto> getPostAccouchementById(@PathVariable int idPostAccouchement) {
        PostAccouchementDto postAccouchementDto  = postAccouchementService.getById(idPostAccouchement);
        return new ResponseEntity<>(postAccouchementDto, HttpStatus.OK);
    }

    @GetMapping("/accouchement/{idAccouchement}")
    public ResponseEntity<PostAccouchementDto> getPostAccouchementByIdAccouchement(@PathVariable int idAccouchement) {
        PostAccouchementDto postAccouchementDto  = postAccouchementService.getByIdAccouchement(idAccouchement);
        return new ResponseEntity<>(postAccouchementDto, HttpStatus.OK);
    }

    @PutMapping("/{idPostAccouchement}")
    public ResponseEntity<PostAccouchementDto> updatePostAccouchement (@PathVariable int idPostAccouchement,@RequestBody PostAccouchementDto postAccouchementDto){
        PostAccouchementDto postAccouchementDtoUpdated = postAccouchementService.update(idPostAccouchement,postAccouchementDto);
        return new ResponseEntity<>(postAccouchementDtoUpdated, HttpStatus.OK);
    }

}
