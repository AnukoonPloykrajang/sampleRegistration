package com.example.services;

import java.util.List;

import com.example.model.UserProfile;

public interface RegistrationService {
	String createNewUser(UserProfile newUser);
	List<UserProfile> getAllUserData();
}
