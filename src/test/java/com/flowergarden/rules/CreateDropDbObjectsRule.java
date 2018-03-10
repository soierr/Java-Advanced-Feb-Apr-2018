/**
 * 
 */
package com.flowergarden.rules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.rules.ExternalResource;

/**
 * @author SOIERR
 *
 */
/*Here we create a rule for every DB related test
 * it creates all the needed db objects from scratch
 * we assume that it get run locally on dev machine in some light db without 
 * too much resources affected*/
public class CreateDropDbObjectsRule extends ExternalResource{
	
	private final String DB_NAME_SQLITE = "flowergarden-dev.db";
	private final String DB_DDL_FILENAME_CREATE = "resources/sql-ddl-create.txt";
	private final String DB_DDL_FILENAME_DROP = "resources/sql-ddl-drop.txt";
	private final String DB_DML_FILENAME_SAMPLE = "resources/sql-dml-sample.txt";
	
	String url = "jdbc:sqlite:" + DB_NAME_SQLITE;
	Connection conn = null;
	
	File fileDb = null;

	@Override
	public void before() throws Throwable {
		
		try{
			File file = new File(DB_NAME_SQLITE);
			Files.delete(file.toPath());
		}catch(NoSuchFileException nsfe){
			
			System.out.println("File not found. It's ok, moving on to the next step");
		}
		
		conn = DriverManager.getConnection(url);
		
		if (conn != null) {
        	
            DatabaseMetaData meta = conn.getMetaData();
            System.out.println("The driver name is " + meta.getDriverName());
            System.out.println("A new database [" + DB_NAME_SQLITE + "] has been created.");
            
        }
        
        performSQL(conn, new File(DB_DDL_FILENAME_CREATE));
        performSQL(conn, new File(DB_DML_FILENAME_SAMPLE));
        
	}
	
	public Properties getSqlScripts(){
		
		Properties sql = new Properties();
		
		try{
			sql.load(new FileInputStream(new File("resources/sql-scripts.txt")));
			
		}catch(IOException e){
			
			e.printStackTrace();
		}
		
		return sql;
		
	}
	
	public Connection getConnection(){
		
		try{
			return !conn.isClosed() ? conn : (conn = DriverManager.getConnection(url));
		}catch(SQLException se){
			se.printStackTrace();
		}
		
		return conn;
		
	}
		
	@Override
	public void after(){
		
		try{
			
			if(conn.isClosed()){
				
				conn = DriverManager.getConnection(url);
				
			}
			
			performSQL(conn, new File(DB_DDL_FILENAME_DROP));
			
		}catch(SQLException se){
			
			se.printStackTrace();
			
		}catch(IOException ioe){
			
			ioe.printStackTrace();
			
		}finally{
			
			try{
				
				conn.close();
				File file = new File(DB_NAME_SQLITE);
				Files.delete(file.toPath());
				
			}catch(IOException | SQLException e){
				
				e.printStackTrace();
			}
		}
		
		System.out.println("Tests accomplished. See JUnit output for details");
		System.out.println("All DB objects has been dropped");
		System.out.println("DB removed from disk");
	}
	
	private void performSQL(Connection conn, File file) throws IOException, SQLException{
		
		BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        int c;
        while((c = br.read())!=-1){
        	
        	sb.append((char)c);
        	
        	while((c = br.read()) != ';'){
        		
        		sb.append((char)c);
        	}
        	
        	sb.append((char)c);
        	
        	Statement stmt = conn.createStatement();
        	stmt.execute(sb.toString());
        	sb.setLength(0);
        	
        }
        
        br.close();
	}

}
