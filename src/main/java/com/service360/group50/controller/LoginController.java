package com.service360.group50.controller;

import com.service360.group50.auth.AuthenticationRequest;
import com.service360.group50.auth.AuthenticationResponse;
import com.service360.group50.auth.UserRegisterRequest;
import com.service360.group50.dto.UsersDTO;
import com.service360.group50.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/signup/customer")
    public ResponseEntity<AuthenticationResponse> customerRegister(
            @RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok ( loginService.customerRegister ( request ) );

    }

    @PostMapping("/signup/advertiser")
    public ResponseEntity<AuthenticationResponse> advertiserRegister(
            @ModelAttribute UserRegisterRequest request,
            @RequestParam("files") MultipartFile[] files
    ) {
        String message = "";
        try{
            message = "Uploaded the files successfully: ";
            return ResponseEntity.ok ( loginService.advertiserRegister ( request, files ) );
        } catch (Exception e) {
            message = "Could not upload the files: ";
            return ResponseEntity.status( HttpStatus.EXPECTATION_FAILED).body(new AuthenticationResponse (message));
        }
    }
    @PostMapping("/signup/serviceprovider")
    public ResponseEntity<AuthenticationResponse> serviceProviderRegister(
            @ModelAttribute  UserRegisterRequest request,
            @RequestParam("files") MultipartFile[] files
    ) {
        String message = "";
        try{
            message = "Uploaded the files successfully: ";
            return ResponseEntity.ok ( loginService.serviceProviderRegister ( request, files ) );
        } catch (Exception e) {
            message = "Could not upload the files: ";
            return ResponseEntity.status( HttpStatus.EXPECTATION_FAILED).body(new AuthenticationResponse (message));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login( @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok ( loginService.login ( request).getBody ( ) );
    }

    @GetMapping("/login/{email}")
    public ResponseEntity<UsersDTO> getUserDetailsByEmail( @PathVariable String email) {

        UsersDTO userDetails = loginService.getUserDetails(email);
        if (userDetails != null) {
            return ResponseEntity.ok(userDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/confirm")
    public RedirectView confirm( @RequestParam("token") String token) {
        return loginService.confirmToken(token);
    }

}