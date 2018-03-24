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

	
	private final String url;
	
	Connection conn = null;
	
	/**
	 * 
	 */
	public DaoDataSourceSqlLiteImpl(String url) {

		this.url = url;
	}
	
	public Connection getConnection(){
		
		try{
			
			conn = DriverManager.getConnection(url);
		}catch(SQLException se){
			
			se.printStackTrace();
		}
		
		return conn;
	}

}
