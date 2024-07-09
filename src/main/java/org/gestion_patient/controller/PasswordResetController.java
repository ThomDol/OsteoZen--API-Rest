package org.gestion_patient.controller;
import lombok.AllArgsConstructor;
import org.gestion_patient.service.PasswordResetTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/password")
public class PasswordResetController {

    private PasswordResetTokenService passwordResetTokenService;



    @PostMapping("/forgot")
    public ResponseEntity<String> processForgotPassword(@RequestParam("email") String email) throws Exception {
        passwordResetTokenService.forgotPassword(email);
        return new ResponseEntity<>("email sent with link to reset password",HttpStatus.OK);
        }


    @PostMapping("/reset")
    public ResponseEntity<String> processResetPassword(@RequestParam("token") String token, @RequestParam("password") String password) {
        passwordResetTokenService.resetPassword(password,token);
        return new ResponseEntity<>("password saved",HttpStatus.OK);
    }

}
