package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;
import utils.DB;

public class ManageUsers {
	
	private DB db = null ;
	
	public ManageUsers() {
		try {
			db = new DB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void finalize() {
		try {
			db.disconnectBD();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
		
	// Add new user
	public void addUser(String username, String mail, String pwd, String nickname, String gender, String birth) {
		String query = "INSERT INTO users (username,mail,pwd,nickname,gender,birth) VALUES (?,?,?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,username);
			statement.setString(2,mail);
			statement.setString(3,pwd);
			statement.setString(4,nickname);
			statement.setString(5,gender);
			statement.setString(6,birth);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*Check if all the fields are filled correctly */
	public boolean isComplete(User user) {
	    return(hasValue(user.getUsername()) &&
	    	   hasValue(user.getMail()) &&
	    	   hasValue(user.getPwd1()) &&
	           hasValue(user.getPwd2()) &&
	    	   hasValue(user.getNickname()) &&
	           hasValue(user.getGender()) &&
	           hasValue(user.getBirth()) );
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
		
	
	// TODO: add other methods 
	/*Check if all the fields are filled correctly */
	
	// Request if parameter is unique
	public boolean requestUniqueness(String attribute, String value) {
		String query = "SELECT * FROM users WHERE " + attribute + "='" + value + "'";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			Boolean bool = !result.next();
			statement.close();
			return bool;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
}
