package com.example.controllers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.UserProfile;
import com.example.services.RegistrationService;

@RestController
public class RegisterationController {
	
	@Autowired
	private RegistrationService service;
	
	@PostMapping("/createNewUser")
    public String createNewUser(@RequestBody UserProfile newUser) {
        return service.createNewUser(newUser);
    }
	
	@GetMapping("/allData")
    public List<UserProfile> getAllUserData() {
        return service.getAllUserData();
    }
	

}
