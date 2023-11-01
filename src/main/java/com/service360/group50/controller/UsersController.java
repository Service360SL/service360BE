package com.service360.group50.controller;

import com.service360.group50.dto.UsersDTO;
import com.service360.group50.entity.Contact;
import com.service360.group50.entity.SystemReview;
import com.service360.group50.entity.Users;
import com.service360.group50.message.ResponseMessage;
import com.service360.group50.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;


import java.security.Principal;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class UsersController {
    private final UserService userService;

    @PutMapping("/updateUser")
    public ResponseEntity<ResponseMessage> updateUser(
        @RequestParam(value = "userId") Long userId,
        @RequestParam(value = "firstName", required = false) String firstName,
        @RequestParam(value = "lastName", required = false) String lastName,
        @RequestParam(value = "password", required = false) String password,
        @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
        @RequestParam(value = "address", required = false) String address,
        @RequestParam(value = "nic", required = false) String nic,
        @RequestParam(value = "profilePic", required = false) String profilePic
    ){
        try {
            userService.updateUser(userId, firstName, lastName, password, phoneNumber, address, nic, profilePic);
            return ResponseEntity.status( HttpStatus.OK).body(new ResponseMessage("User updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("User could not be updated"));
        }
    }

    @GetMapping("/getUserById/{userId}")
    public UsersDTO getUserById( @PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @PostMapping("/addSystemReview")
    public SystemReview addSystemReview(
                @RequestParam("userid") Long userId,
                @RequestParam("review") String review,
                @RequestParam("rating") int rating) {
        return userService.addSystemReview(userId, review, rating);
    }

    @GetMapping("/getAllSystemReview")
    public Iterable<SystemReview> getAllSystemReview() {
        return userService.getAllSystemReview();
    }

    @PostMapping("/addContactMessage")
    public Contact addContactMessage(
            @RequestParam("email") String email,
            @RequestParam("fullName") String fullName,
            @RequestParam("contactNumber") String contactNumber,
            @RequestParam("message") String message
    ){
        return userService.addContactMessage(email,fullName,contactNumber,message);
    }

}
