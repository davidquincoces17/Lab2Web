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
		String query = "INSERT INTO funny (id,parentID,authorID,content,timestamp) VALUES (?,?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,funny.getId());
			statement.setInt(2,funny.getParentId());
			statement.setInt(3,funny.getAuthorId());
			statement.setTimestamp(4,funny.getTimestamp());
			statement.setString(5,funny.getContent());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Delete existing funny */
	public void deleteFunny(Integer id) {
		String query = "DELETE FROM tweets WHERE id = ?";
		PreparedStatement statement = null;
		try {
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
		 String query = "SELECT funny.id,funny.parentID,funny.authorID,funny.timestamp,funny.content,user.username, user.nickname FROM funny INNER JOIN user ON funny.authorID = user.id where funny.authorID = ? ORDER BY funny.timestamp DESC LIMIT ?,? ;";
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
				 l.add(funny);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	
	
	
	
	
	
	
	
	/* TODO --> GETfOLLOWSEDfUNNIES FUNCTIONALITY  */
	
	public List<Funny> getFollowedFunnies(Integer authorID,Integer start, Integer end) {
		
		/*
		 String query = "SELECT funny.id,funny.parentID,funny.authorID,funny.timestamp,funny.content,user.username FROM funny INNER JOIN user ON funny.authorID = user.id where funny.authorID = ? ORDER BY funny.timestamp DESC LIMIT ?,? ;";
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
				 l.add(funny);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
		*/
		return null;
	}
	
	
	
}