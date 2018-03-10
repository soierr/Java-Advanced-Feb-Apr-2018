/**
 * 
 */
package com.flowergarden.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.flowergarden.dao.DaoDataSource;

/**
 * @author SOIERR
 *
 */
public class DaoDataSourceSqlLiteImpl implements DaoDataSource{
	
	private final String DB_NAME_SQLITE = "flowergarden.db";
	
	private String url = "jdbc:sqlite:" + DB_NAME_SQLITE;
	
	Connection conn = null;
	
	public Connection getConnection(){
		
		try{
			
			conn = DriverManager.getConnection(url);
		}catch(SQLException se){
			
			se.printStackTrace();
		}
		
		return conn;
	}

}
