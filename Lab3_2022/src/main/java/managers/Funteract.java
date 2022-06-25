package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Funny;
import models.User;
import utils.DB;


public class Funteract {
	
	private DB db = null ;
	
	public Funteract() {
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
	
	/* Like a funny */
	public void likeFunny(User user, Funny funny) {
		String query = "INSERT INTO funstate (userID, funnyID, state) VALUES (?,?,1)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1, user.getId());
			statement.setInt(2, funny.getId());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Dislike a funny */
	public void dislikeFunny(User user, Funny funny) {
		String query = "INSERT INTO funstate (userID, funnyID, state) VALUES (?,?,0)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1, user.getId());
			statement.setInt(2, funny.getId());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}