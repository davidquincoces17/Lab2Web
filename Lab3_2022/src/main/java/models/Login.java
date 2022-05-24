package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DB;

public class Login {

	private String mail = "";
	private String pass = "";
	private boolean[] error = {false, false};
	
	public String getMail(){
		return mail;
	}
	
	public void setMail(String mail){
		this.mail = mail;
	}
	
	public String getPass(){
		return pass;
	}
	
	public void setPass(String pass){
		this.pass = pass;
	}

	public boolean[] getError() {
		return error;
	}
	
	public boolean isComplete() {
	    return(hasValue(getMail()) && hasValue(getPass()));
	}
	
	public boolean canLogin() {
		String query = "SELECT * FROM users WHERE mail='" + this.mail + "' AND pwd='"+ this.pass + "'";

		PreparedStatement statement = null;
		
		DB db = null ;
		
		try {
			db = new DB();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
	
}