package com.example.dao;

import java.util.List;

import com.example.model.UserProfile;

public interface RegistrationDao {
	String createNewUser(UserProfile newUser);
	List<UserProfile> getAllUserData();
}
