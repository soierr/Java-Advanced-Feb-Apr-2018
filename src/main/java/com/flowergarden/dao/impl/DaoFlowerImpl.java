/**
 * 
 */
package com.flowergarden.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.flowergarden.dao.DaoDataSource;
import com.flowergarden.dao.DaoFlower;
import com.flowergarden.flowers.GeneralFlower2;
import com.flowergarden.properties.FreshnessInteger;

/**
 * @author SOIERR
 *
 */
@Repository("daoFlower")
public class DaoFlowerImpl implements DaoFlower{
	
	private String SQL_SELECT_FLOWER_BY_ID = "sqlSelectFlowerById";
	
	private String SQL_INSERT_FLOWER = "sqlInsertFlower";
	private String SQL_UPDATE_FLOWER = "sqlUpdateFlower";
	private String SQL_DELETE_FLOWER = "sqlDeleteFlower";
	
	private String SQL_GET_LAST_ID = "select last_insert_rowid()";
	
	@Resource(type=DaoDataSource.class)
	private DaoDataSource dataSource = null;
	
	@Resource(name="sqlScripts")
	private Properties sql = null;

	public DaoFlowerImpl() {
	}
	
	public DaoFlowerImpl(DaoDataSource dataSource, Properties sql) {
		
		this.dataSource = dataSource;
		this.sql = sql;
	}
	
	@Override
	public int create(GeneralFlower2 flower) {

		int createdId = 0;
		
		Connection conn = null;
		
		try{
			
			conn = dataSource.getConnection();
			
			PreparedStatement stmtIns = conn.prepareStatement(sql.getProperty(SQL_INSERT_FLOWER));
			PreparedStatement stmtSel = conn.prepareStatement(SQL_GET_LAST_ID);
			
			stmtIns.setString(1, flower.getName());
			stmtIns.setInt(2, flower.getLength());
			stmtIns.setInt(3, flower.getFreshness().getFreshness());
			stmtIns.setLong(4, flower.getPriceLong());
			stmtIns.setInt(5, flower.getPetals());
			stmtIns.setBoolean(6, flower.isSpike());
			stmtIns.setInt(7, flower.getBouquetId());
			
			conn.setAutoCommit(false);

			stmtIns.execute();
			stmtSel.execute();
			
			createdId = stmtSel.getResultSet().getInt(1);
			
			conn.commit();
			
		}catch(SQLException se){
			
			se.printStackTrace();
			
		}finally{
			
			try{
				conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
		return createdId;
	}

	@Override
	public void update(GeneralFlower2 newFlower) {
		
		Connection conn = null;
		
		try{
			
			conn = dataSource.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(sql.getProperty(SQL_UPDATE_FLOWER));
			
			stmt.setInt(1, newFlower.getId());
			stmt.setString(2, newFlower.getName());
			stmt.setInt(3, newFlower.getLength());
			stmt.setInt(4, newFlower.getFreshness().getFreshness());
			stmt.setLong(5, newFlower.getPriceLong());
			stmt.setInt(6, newFlower.getPetals());
			stmt.setBoolean(7, newFlower.isSpike());
			stmt.setInt(8, newFlower.getBouquetId());

			stmt.execute();
			
		}catch(SQLException se){
			
			se.printStackTrace();
			
		}finally{
			
			try{
				conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
	}
	

	@Override
	public void delete(int flowerId) {
		
		Connection conn = null;
		
		try{
			
			conn = dataSource.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(sql.getProperty(SQL_DELETE_FLOWER));
			
			stmt.setInt(1, flowerId);
			stmt.execute();
			
		}catch(SQLException se){
			
			se.printStackTrace();
			
		}finally{
			
			try{
				conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
	}
	
	@Override
	public GeneralFlower2 getFlower(int flowerId) {
		
		Connection conn = null;
		
		GeneralFlower2 flower = null;
		
		try{
			
			conn = dataSource.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(sql.getProperty(SQL_SELECT_FLOWER_BY_ID));
			
			stmt.setInt(1, flowerId);
			stmt.execute();
			
			ResultSet rs = stmt.getResultSet();
			int i = 1;
				
			if(!rs.next()){
				
				return null;
			}
			
			 flower = new GeneralFlower2();
				
			flower.setId(rs.getInt(i++));
			flower.setName(rs.getString(i++));
			flower.setLength(rs.getInt(i++));
			flower.setFreshness(new FreshnessInteger(rs.getInt(i++)));
			flower.setPriceLong(rs.getLong(i++));
			flower.setPetals(rs.getInt(i++));
			flower.setSpike(rs.getBoolean(i++));
			flower.setBouquetId(rs.getInt(i++));
			
			
		}catch(SQLException se){
			
			se.printStackTrace();
			
		}finally{
			
			try{
				conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
			
		}
		
		return flower;
	}

}
