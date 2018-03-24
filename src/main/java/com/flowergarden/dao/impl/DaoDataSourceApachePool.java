/**
 * 
 */
package com.flowergarden.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

import com.flowergarden.dao.DaoDataSource;

/**
 * @author SOIERR
 *
 */
public class DaoDataSourceApachePool implements DaoDataSource{
	
	BasicDataSource datasource = null;
	
	Connection conn = null;

	public DaoDataSourceApachePool(BasicDataSource datasource) {
		
		this.datasource = datasource;
	}
	
	@Override
	public Connection getConnection() {
		
		try{
			
			conn = datasource.getConnection();
			
		}catch(SQLException se){
			
			se.printStackTrace();
		}
		
		return conn;
	}

}
