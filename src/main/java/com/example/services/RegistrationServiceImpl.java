package com.example.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.RegistrationDao;
import com.example.model.UserProfile;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	private RegistrationDao dao;

	@Override
	public String createNewUser(UserProfile newUser) {
		if(newUser.getUserName()==null || "".equals(newUser.getUserName().trim())) {
			return "User Name Empty";
		}
		if(newUser.getPassword()==null || "".equals(newUser.getPassword().trim())) {
			return "Password Empty";
		}
		if(newUser.getFirstName()==null || "".equals(newUser.getFirstName().trim())) {
			return "First Name Empty";
		}
		if(newUser.getLastName()==null || "".equals(newUser.getLastName().trim())) {
			return "Last Name Empty";
		}
		if(newUser.getPhoneNumber()==null || "".equals(newUser.getPhoneNumber().trim())) {
			return "Phone Number Empty";
		}
		if(newUser.getSalary()==null) {
			return "Salary Empty";
		}
		
		if(newUser.getSalary()>50000) {
			newUser.setMemberType("Platinum");
		}else if(newUser.getSalary()>=30000) {
			newUser.setMemberType("Gold");
		}else if (newUser.getSalary()>=15000) {
			newUser.setMemberType("Silver");
		}else {
			return "Invalid Salary";
		}
		
		Date currentDate = Calendar.getInstance(Locale.US).getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		newUser.setRefId(sdf.format(currentDate)+newUser.getPhoneNumber().substring(newUser.getPhoneNumber().length()-5, newUser.getPhoneNumber().length()));
		
		return dao.createNewUser(newUser);
	}

	@Override
	public List<UserProfile> getAllUserData() {
		return dao.getAllUserData();
	}
	
	

}
