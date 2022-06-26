package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Funny;
import utils.DB;


public class ManageFunnies {
	
	private DB db = null ;
	
	public ManageFunnies() {
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
	
	/* Add a funny */
	public void addFunny(Funny funny) {
		String query = "INSERT INTO funny (id,parentID,authorID,content,timestamp) VALUES (DEFAULT,DEFAULT,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			//statement.setInt(1,funny.getId());
			//statement.setInt(2,funny.getParentId());
			statement.setInt(1,funny.getAuthorId());
			statement.setString(2,funny.getContent());
			statement.setTimestamp(3,funny.getTimestamp());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Delete existing funny */
	public void deleteFunny(Integer id) {
		String query = "DELETE FROM funny WHERE id = ?";
		String query2 = "DELETE FROM funstate WHERE funnyID = ?";
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		try {
			
			statement2 = db.prepareStatement(query2);
			statement2.setInt(1,id);
			statement2.executeUpdate();
			statement2.close();
			
			statement = db.prepareStatement(query);
			statement.setInt(1,id);
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/* Get funnies from a user given start and end*/
	public List<Funny> getUserFunnies(Integer authorID,Integer start, Integer end) {
		 String query = "SELECT funny.id,funny.parentID,funny.authorID,funny.timestamp,funny.content,user.username,user.nickname,user.profilePhoto FROM funny INNER JOIN user ON funny.authorID = user.id where funny.authorID = ? ORDER BY funny.timestamp DESC LIMIT ?,? ;";
		 PreparedStatement statement = null;
		 List<Funny> l = new ArrayList<Funny>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,authorID);
			 statement.setInt(2,start);
			 statement.setInt(3,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Funny funny = new Funny();
				 funny.setId(rs.getInt("id"));
				 funny.setParentId(rs.getInt("parentID"));
				 funny.setAuthorId(rs.getInt("authorID"));
				 funny.setTimestamp(rs.getTimestamp("timestamp"));
				 funny.setContent(rs.getString("content"));
				 funny.setAuthorNickname(rs.getString("nickname"));
				 funny.setAuthorUsername(rs.getString("username"));
				 funny.setImage(rs.getString("profilePhoto"));
				 l.add(funny);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	
	public List<Funny> getFollowedFunnies(Integer userID,Integer start, Integer end) {
		
		String query = "SELECT funny.id,funny.parentID,funny.authorID,funny.timestamp,funny.content,user.username,user.nickname,user.profilePhoto FROM funny INNER JOIN user ON funny.authorID = user.id where funny.authorID IN (SELECT followedUser FROM follow WHERE userID = ? UNION SELECT ?) ORDER BY funny.timestamp DESC LIMIT ?,? ;";
		 PreparedStatement statement = null;
		 List<Funny> l = new ArrayList<Funny>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,userID);
			 statement.setInt(2,userID);
			 statement.setInt(3,start);
			 statement.setInt(4,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Funny funny = new Funny();
				 funny.setId(rs.getInt("id"));
				 funny.setParentId(rs.getInt("parentID"));
				 funny.setAuthorId(rs.getInt("authorID"));
				 funny.setTimestamp(rs.getTimestamp("timestamp"));
				 funny.setContent(rs.getString("content"));
				 funny.setAuthorNickname(rs.getString("nickname"));
				 funny.setAuthorUsername(rs.getString("username"));
				 funny.setImage(rs.getString("profilePhoto"));
				 l.add(funny);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	public int getLikes(int id) {
		String query = "SELECT COUNT(*) AS `funs` FROM `funstate` WHERE (state = 1) AND (`funnyID` = ?)";
		PreparedStatement statement = null;
		int value = 0;
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,id);
			 ResultSet rs = statement.executeQuery();
			 rs.next();
			 value = rs.getInt("funs");
			 rs.close();
			 statement.close();
			 } catch (SQLException e) {
					e.printStackTrace();
				}
		return value;
	}
	
	public int getDislikes(int id) {
		String query = "SELECT COUNT(`funnyID`) FROM `funstate` WHERE (state = 0) AND (`funnyID` = ?)";
		PreparedStatement statement = null;
		int value = 0;
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,id);
			 ResultSet rs = statement.executeQuery();
			 rs.next();
			 value = rs.getInt("COUNT(`funnyID`)");
			 rs.close();
			 statement.close();
			 } catch (SQLException e) {
					e.printStackTrace();
				}
		return value;
	}
	
	public int getFunnyReaction(Integer userID,Integer funnyID) {
		String query = "SELECT state FROM `funstate` WHERE (`userID` = ?) AND (`funnyID` = ?)";
		PreparedStatement statement = null;
		int value = 0;
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,userID);
			 statement.setInt(2,funnyID);
			 ResultSet rs = statement.executeQuery();
			 rs.next();
			 value = rs.getInt("state");
			 rs.close();
			 statement.close();
		 }catch (SQLException e) {
			return 3;
		}
		return value;
	}
	
public List<Funny> getFunnySearch(String inputContent,Integer start, Integer end) {
		
		String query = "SELECT funny.id,funny.parentID,funny.authorID,funny.timestamp,funny.content,user.username,user.nickname,user.profilePhoto FROM funny INNER JOIN user ON funny.authorID = user.id WHERE content LIKE ? ORDER BY funny.timestamp DESC LIMIT ?,? ;";
		System.out.print(query); 
		PreparedStatement statement = null;
		 List<Funny> l = new ArrayList<Funny>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setString(1,inputContent);
			 statement.setInt(2,start);
			 statement.setInt(3,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Funny funny = new Funny();
				 funny.setId(rs.getInt("id"));
				 funny.setParentId(rs.getInt("parentID"));
				 funny.setAuthorId(rs.getInt("authorID"));
				 funny.setTimestamp(rs.getTimestamp("timestamp"));
				 funny.setContent(rs.getString("content"));
				 funny.setAuthorNickname(rs.getString("nickname"));
				 funny.setAuthorUsername(rs.getString("username"));
				 funny.setImage(rs.getString("profilePhoto"));
				 l.add(funny);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
}