package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import models.User;
import utils.DB;

public class ManageUsers {
	
	private DB db = null ;
	
	public ManageUsers() {
		try {
			db = DB.getDB();
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
	public void addUser(String username, String mail, String pwd, String nickname, String gender, String birth, String profilePhoto, boolean isAdmin) {
		String query = "INSERT INTO user (username,email,password,nickname,gender,birth,profilePhoto,isAdmin) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,username);
			statement.setString(2,mail);
			statement.setString(3,pwd);
			statement.setString(4,nickname);
			statement.setInt(5,Integer.parseInt(gender));
			statement.setString(6,birth);
			statement.setString(7, profilePhoto);
			statement.setBoolean(8, isAdmin);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	/* Get a user given its PK*/
	public User getUser(Integer id) {
		String query = "SELECT id,name,mail FROM user WHERE id = ? ;";
		PreparedStatement statement = null;
		ResultSet rs = null;
		User user = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,id);
			rs = statement.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setMail(rs.getString("mail"));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	// Get all the users
	public List<User> getUsers(Integer start, Integer end) {
		 String query = "SELECT id,name FROM user ORDER BY name ASC LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<User> l = new ArrayList<User>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,start);
			 statement.setInt(2,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 User user = new User();
				 user.setId(rs.getInt("id"));
				 user.setUsername(rs.getString("username"));
				 l.add(user);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
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
	
	/*Check if all the fields for Login are filled correctly */
	public boolean isLoginComplete(User user) {
		System.out.println(user);
	    return(hasValue(user.getMail()) &&
	    	   hasValue(user.getPwd1()) );
	}
	
	
	/*Check if user can login */
	public Pair<Boolean,User> canLogin(User user) {
		String query = "SELECT * FROM user WHERE email='" + user.getMail() + "' AND password='"+ user.getPwd1() + "'";

		PreparedStatement statement = null;
		
		DB db = null ;
		
		try {
			db = DB.getDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean can = false;
		
		try {
			statement = db.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				user.setId(result.getInt("id"));
				user.setMail(result.getString("email"));
				can = true;
			} else {
				boolean[] myErrors = user.getError();
				myErrors[9] = true;
				user.setError(myErrors);
			}
			
			result.close();
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			can = false;
		}
		System.out.print(can);
		System.out.print(user);
		return Pair.of(can, user);
	}
	
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
		
	
	// TODO: add other methods 
	/*Check if all the fields are filled correctly */
	
	// Request if parameter is unique
	public boolean requestUniqueness(String attribute, String value) {
		String query = "SELECT * FROM user WHERE " + attribute + "='" + value + "'";
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
