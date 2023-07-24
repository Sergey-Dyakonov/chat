package com.soft.chat.controller;

import com.soft.chat.dto.LoginUserCredentials;
import com.soft.chat.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    ResponseEntity<Void> loginUser(@RequestBody LoginUserCredentials userCredentials) {
        userService.checkUserCredentials(userCredentials);
        return ResponseEntity.ok().build();
    }
}