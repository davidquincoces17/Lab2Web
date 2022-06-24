package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Venda;
import utils.DB;

public class ManageVenda {
	
	private DB db = null ;
	
	public ManageVenda() {
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
	public void addVenda(String data, String hora, String pagament, String client, String venedor) {
		String query = "INSERT INTO venda (data,hora,forma_pagament,id,dni) VALUES (?,?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,data);
			statement.setString(2,hora);
			statement.setString(3,pagament);
			statement.setString(4,client);
			statement.setString(5,venedor);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
		
	public boolean isComplete(Venda user) {
	    return(hasValue(user.getDate()) &&
	    	   hasValue(user.getHora()) &&
	    	   hasValue(user.getPagament()) &&
	           hasValue(user.getClient()) &&
	    	   hasValue(user.getVenedor()) );
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
