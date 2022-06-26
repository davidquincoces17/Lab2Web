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
		String query = "SELECT funnyID FROM funstate WHERE (userID=? AND funnyID=?) AND state=?;";
		String query2 = "UPDATE funstate SET state=1 WHERE userID=? AND funnyID=?;";
		String query3 = "INSERT INTO funstate (userID, funnyID, state) VALUES (?,?,1)";

		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		PreparedStatement statement4 = null;
		
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1, user.getId());
			statement.setInt(2, funny.getId());
			statement.setInt(3, 0);
			ResultSet rs = statement.executeQuery();
			
			statement3 = db.prepareStatement(query);
			statement3.setInt(1, user.getId());
			statement3.setInt(2, funny.getId());
			statement3.setInt(3, 1);
			ResultSet rs3 = statement3.executeQuery();
			
			if(rs.first() == true) {//en cas que li hagi donat DISLIKE anteriorment, n'actualitza la row per donar-li LIKE
				statement2 = db.prepareStatement(query2);
				statement2.setInt(1, user.getId());
				statement2.setInt(2, funny.getId());
				statement2.executeUpdate();
				statement2.close();
			}else if(rs3.first() == false){ //en cas que no li ha donat like abans -> afegeix el like
				statement4 = db.prepareStatement(query3);
				statement4.setInt(1, user.getId());
				statement4.setInt(2, funny.getId());
				statement4.executeUpdate();
			}else { //En cas de que ja li hagi donat like previament i li torni a donar al botó de like, cridem a la query per desfer el like
				String update = "DELETE FROM funstate WHERE (userID=? AND funnyID=?);";
				PreparedStatement removeLike = null;
				try {
					removeLike = db.prepareStatement(update);
					removeLike.setInt(1, user.getId());
					removeLike.setInt(2, funny.getId());
					removeLike.executeUpdate();
					removeLike.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/* Dislike a funny */
	public void dislikeFunny(User user, Funny funny) {
		String query = "SELECT funnyID FROM funstate WHERE (userID=? AND funnyID=?) AND state=?;";
		String query2 = "UPDATE funstate SET state=0 WHERE userID=? AND funnyID=?;";
		String query3 = "INSERT INTO funstate (userID, funnyID, state) VALUES (?,?,0)";

		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		PreparedStatement statement4 = null;
		
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1, user.getId());
			statement.setInt(2, funny.getId());
			statement.setInt(3, 1);
			ResultSet rs = statement.executeQuery();
			
			statement3 = db.prepareStatement(query);
			statement3.setInt(1, user.getId());
			statement3.setInt(2, funny.getId());
			statement3.setInt(3, 0);
			ResultSet rs3 = statement3.executeQuery();
			
			if(rs.first() == true) {//en cas que li hagi donat LIKE anteriorment, n'actualitza la row per donar-li DISLIKE
				statement2 = db.prepareStatement(query2);
				statement2.setInt(1, user.getId());
				statement2.setInt(2, funny.getId());
				statement2.executeUpdate();
				statement2.close();
			}else if(rs3.first() == false){ //en cas que no li ha donat dislike abans -> afegeix el dislike
				statement4 = db.prepareStatement(query3);
				statement4.setInt(1, user.getId());
				statement4.setInt(2, funny.getId());
				statement4.executeUpdate();
			}else { //En cas de que ja li hagi donat dislike previament i li torni a donar al botó de dislike, cridem a la query per desfer el dislike
				String update = "DELETE FROM funstate WHERE (userID=? AND funnyID=?);";
				PreparedStatement removeDislike = null;
				try {
					removeDislike = db.prepareStatement(update);
					removeDislike.setInt(1, user.getId());
					removeDislike.setInt(2, funny.getId());
					removeDislike.executeUpdate();
					removeDislike.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	}