package com.adi.database;



import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;



public class DatabaseEngine implements AutoCloseable {

	private String url;
	private String driver;
	private String username;
	private String password;
	private String dbName;
	private String tableName;
	private String preparedQuery;
	private boolean autoCommit;
	
	private Connection connection;
	private PreparedStatement preparedStatement;
	
	private static final String URL = "url";
	private static final String DRIVER = "driver";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String DB_NAME = "db-name";
	private static final String TABLE_NAME = "table-name";
	private static final String PREPARED_QUERY = "prepared-query";
	private static final String AUTO_COMMIT = "auto-commit";
	
	private static final String TRUE = "true";
	private static final String FALSE = "false";

	
		
	public DatabaseEngine(File file) throws IOException, ClassNotFoundException {
	
		Properties props = new Properties();
		FileInputStream fistream = new FileInputStream(file);
		
		props.load(fistream);
		fistream.close();
		
		url = props.getProperty(URL);
		driver = props.getProperty(DRIVER);
		username = props.getProperty(USERNAME);
		password = props.getProperty(PASSWORD);
		dbName = props.getProperty(DB_NAME);
		tableName = props.getProperty(TABLE_NAME);
		preparedQuery = props.getProperty(PREPARED_QUERY);
		autoCommit = props.getProperty(AUTO_COMMIT).equals(TRUE) ? true : false;	
		
		Class.forName(driver);
	}
	
	
	
	public void connect() throws IOException, SQLException {
	
		connection = DriverManager.getConnection(url+dbName, username, password);
		preparedStatement = connection.prepareStatement(preparedQuery);
	}
	
	
	
	public void feed(String fileName, byte[] bytes) throws SQLException {
	
		preparedStatement.setString(1, fileName);
		preparedStatement.setBytes(2, bytes);
		
		preparedStatement.execute();
		
		if(!autoCommit)
			connection.commit();
	}
	
	
	
	@Override
	public void close() throws SQLException {
	
		preparedStatement.close();
		connection.close();
	}
	
	
	
	public String getUsername() {
	
		return username;
	}
	
	
	
	public String getPassword() {
	
		return password;
	}
	
	
	
	public String dbName() {
	
		return dbName;
	}
	
	
	
	public String tableName() {
	
		return tableName;
	}
}