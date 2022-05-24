package utils;

import java.sql.*;

public class DB {
	
	private static DB db = null;
	private Connection connection = null;
	
	private DB() throws Exception {
		
		// WITHOUT POOL
		String user = "mysql";
		String password = "prac";
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost/funnierdb?serverTimezone=UTC&user="+user+"&password="+password);

	}
	
	public static DB getDB() throws Exception {
		if (db == null) 
			db = new DB();
		return db;
	}
	
	//execute queries
	public PreparedStatement prepareStatement(String query) throws SQLException{
		// Note that this is done using https://www.arquitecturajava.com/jdbc-prepared-statement-y-su-manejo/
		return connection.prepareStatement(query);
	}
	
	public void disconnectBD() throws SQLException{
		connection.close();
		db = null;
	}
}