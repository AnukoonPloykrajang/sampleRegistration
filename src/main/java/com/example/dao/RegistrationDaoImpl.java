package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.sqlite.SQLiteDataSource;

import com.example.model.UserProfile;

@Repository
public class RegistrationDaoImpl implements RegistrationDao {

	@Override
	public String createNewUser(UserProfile newUser) {
		Connection conn = null ;
		PreparedStatement stmt = null ;
		
		try {
			SQLiteDataSource ds = new SQLiteDataSource();
			ds.setUrl("jdbc:sqlite:C:/Programs-Not-Needed-to-Install/DB/registration.db");
			conn = ds.getConnection();
			StringBuffer query = new StringBuffer();
			
			query.append("INSERT INTO USER_PROFILE values(  " );
			query.append("?,?,?,?,?,?,?,?");
			query.append(" );");
			stmt = conn.prepareStatement(query.toString());
			int index=1;
			stmt.setString(index++, newUser.getRefId());
			stmt.setString(index++, newUser.getUserName());
			stmt.setString(index++, newUser.getPassword());
			stmt.setString(index++, newUser.getFirstName());
			stmt.setString(index++, newUser.getLastName());
			stmt.setString(index++, newUser.getMemberType());
			stmt.setString(index++, newUser.getPhoneNumber());
			stmt.setInt(index++, newUser.getSalary());
			
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "";
	}

	@Override
	public List<UserProfile> getAllUserData() {
		List<UserProfile> listResult = new ArrayList<UserProfile>();
		Connection conn = null ;
		Statement stmt = null ;
		ResultSet rs = null;
		try {
			SQLiteDataSource ds = new SQLiteDataSource();
			ds.setUrl("jdbc:sqlite:C:/Programs-Not-Needed-to-Install/DB/registration.db");
			conn = ds.getConnection();
			stmt = conn.createStatement();
			StringBuffer query = new StringBuffer();
			query.append("SELECT ");
			query.append("REF_ID, USER_NAME, PASSWORD, FIRST_NAME, LAST_NAME, MEMBER_TYPE, PHONE_NUMBER, SALARY ");
			query.append("FROM USER_PROFILE ");
			rs = stmt.executeQuery(query.toString());
			
			if(rs!=null) {
				listResult = rsToResult(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<UserProfile>();
		}
		return listResult;
	}
	
	public List<UserProfile> rsToResult(ResultSet rs) throws Exception {
		List<UserProfile> result = new ArrayList<UserProfile>();
		while(rs.next()) {
			UserProfile model = new UserProfile();
			model.setRefId(rs.getString("REF_ID"));
			model.setUserName(rs.getString("USER_NAME"));
			model.setPassword(rs.getString("PASSWORD"));
			model.setFirstName(rs.getString("FIRST_NAME"));
			model.setLastName(rs.getString("LAST_NAME"));
			model.setMemberType(rs.getString("MEMBER_TYPE"));
			model.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			model.setSalary(rs.getInt("SALARY"));
			result.add(model);
		}
		
		return result;
		
	}
	
	

}
