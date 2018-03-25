/**
 * 
 */
package com.flowergarden.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;

import com.flowergarden.dao.DaoDataSource;

/**
 * @author SOIERR
 *
 */
@Repository
public class DaoDataSourceApachePool implements DaoDataSource{
	
	@Resource(type=BasicDataSource.class)
	BasicDataSource datasource = null;
	
	Connection conn = null;

	public DaoDataSourceApachePool() {
	}
	
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
