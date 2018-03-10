/**
 * 
 */
package com.flowergarden.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.flowergarden.dao.DaoDataSource;
import com.flowergarden.dao.DaoFlower;
import com.flowergarden.flowers.GeneralFlower2;
import com.flowergarden.properties.FreshnessInteger;

/**
 * @author SOIERR
 *
 */
public class DaoFlowerImpl implements DaoFlower{
	
	private String SQL_INSERT_FLOWER = "sqlInsertFlower";
	private String SQL_SELECT_FLOWERS = "sqlSelectFlowers";
	private String SQL_GET_LAST_ID = "select last_insert_rowid()";
	
	private DaoDataSource dataSource = null;
	
	private Properties sql = null;
	
	/**
	 * 
	 */
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
			
			stmtIns.setString(2, flower.getName());
			stmtIns.setInt(3, flower.getLength());
			stmtIns.setInt(4, flower.getFreshness().getFreshness());
			stmtIns.setLong(5, flower.getPriceLong());
			stmtIns.setInt(6, flower.getPetals());
			stmtIns.setBoolean(7, flower.isSpike());
			
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
	public void update(GeneralFlower2 oldFlower, GeneralFlower2 newFlower) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void delete(GeneralFlower2 flower) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<GeneralFlower2> getFlowers() {
		
		List<GeneralFlower2> listFlowers = null;
		
		try(PreparedStatement stmt = dataSource.getConnection().prepareStatement(sql.getProperty(SQL_SELECT_FLOWERS))){
			
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			listFlowers = new ArrayList<GeneralFlower2>();
			int i = 1;
			
			while(rs.next()){
				
				GeneralFlower2 flower = new GeneralFlower2();
					
				flower.setId(rs.getInt(i++));
				flower.setName(rs.getString(i++));
				flower.setLength(rs.getInt(i++));
				flower.setFreshness(new FreshnessInteger(rs.getInt(i++)));
				flower.setPriceLong(rs.getLong(i++));
				flower.setPetals(rs.getInt(i++));
				flower.setSpike(rs.getBoolean(i++));
				i=1;
				
				listFlowers.add(flower);
			}
			
			
		}catch(SQLException se){
			
			se.printStackTrace();
			
		}
		
		return listFlowers;
	}

}
