/**
 * 
 */
package com.flowergarden.rules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.rules.ExternalResource;

/**
 * @author SOIERR
 *
 */
/*Rule to create db and fill it with sample data.*/
public class CreateDbAndSampleDataRule extends ExternalResource{
	
	private final String DB_NAME_SQLITE = "flowergarden.db";
	private final String DB_DDL_FILENAME_CREATE = "resources/sql-ddl-create.txt";
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
			
		}catch(FileSystemException fse){
			
			System.out.println("The file " + DB_NAME_SQLITE + " is busy by other application. Cannont recreate it");
			return;
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
	
	public Connection getConnection(){
		
		try{
			return !conn.isClosed() ? conn : DriverManager.getConnection(url);
		}catch(SQLException se){
			se.printStackTrace();
		}
		
		return conn;
		
	}
		
	@Override
	public void after(){
		
		try{
			
			if(conn == null){
				
				return;
			}
			
			if(!conn.isClosed()){
				
				conn.close();
				
			}

			
		}catch(SQLException se){
			
			se.printStackTrace();
			
		}

		System.out.println("DB should be removed manually");
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
