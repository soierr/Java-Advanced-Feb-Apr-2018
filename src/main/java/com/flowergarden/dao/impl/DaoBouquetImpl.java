/**
 * 
 */
package com.flowergarden.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.Bouquet2;
import com.flowergarden.bouquet.Price;
import com.flowergarden.dao.DaoBouquet;
import com.flowergarden.dao.DaoDataSource;
import com.flowergarden.dao.model.BouquetImpl;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.GeneralFlower2;
import com.flowergarden.properties.FreshnessInteger;

/**
 * @author SOIERR
 *
 */
public class DaoBouquetImpl implements DaoBouquet{
	
	private String SQL_INSERT_BOUQUET = "sqlInsertBouquet";
	private String SQL_SELECT_BOUQUET_BY_ID = "sqlSelectBouquetById";
	private String SQL_SELECT_BOUQUET_BY_NAME = "sqlSelectBouquetByName";
	private String SQL_DELETE_FLOWERS_FROM_BOUQUET = "sqlDeleteFlowersFromBouquet";
	private String SQL_DELETE_BOUQUET_TEMPLATE = "sqlDeleteBouquetTemplate";
	private String SQL_GET_LAST_ID = "select last_insert_rowid()";
	
	private String SQL_SELECT_BOUQUET_PRICE_BY_ID = "sqlSelectBouquetPriceById";
	private String SQL_SELECT_BOUQUET_PRICE_BY_NAME = "sqlSelectBouquetPriceByName";
	
	private String SQL_SELECT_BOUQUETS = "sqlSelectBouquets";
	
	private DaoDataSource dataSource = null;
	
	private Properties sql = null;
	
	/**
	 * 
	 */
	public DaoBouquetImpl(DaoDataSource dataSource, Properties sql) {
	
		this.dataSource = dataSource;
		this.sql = sql;
	}
	
	@Override
	public int createBouquet(int templateId, List<GeneralFlower2> listFlowers) {
		
		int createdId = 0;
		
		Connection conn = null;
		
		try{
			
			conn = dataSource.getConnection();
			
			PreparedStatement stmtIns = null;
			PreparedStatement stmtSel = null; 
			
			Iterator<GeneralFlower2> it = listFlowers.iterator();
			
			while(it.hasNext()){
				
				stmtIns = conn.prepareStatement(sql.getProperty(SQL_INSERT_BOUQUET));
				stmtIns.setInt(1, templateId);
				stmtIns.setInt(2, it.next().getId());
				stmtIns.addBatch();
				
			}
			
			stmtSel = conn.prepareStatement(SQL_GET_LAST_ID);
						
			conn.setAutoCommit(false);

			stmtIns.executeBatch();
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
	public Bouquet2<GeneralFlower2> getBouquet(int id) {
		
		Connection conn = dataSource.getConnection();
		
		Bouquet2<GeneralFlower2> bouquet = null;
		
		ResultSet rs = null;
		
		try{
			
			PreparedStatement stmt = conn.prepareStatement(sql.getProperty(SQL_SELECT_BOUQUET_BY_ID));
			stmt.setInt(1, id);
			
			stmt.execute();
			rs = stmt.getResultSet();			
			bouquet = getBouquet(rs);
			
		}catch(SQLException se){
			
			se.printStackTrace();
			
		}finally{
			
			try{
				conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
		return bouquet;

	}

	@Override
	public Bouquet2<GeneralFlower2> getBouquet(String name) {
		
		Connection conn = dataSource.getConnection();
		
		Bouquet2<GeneralFlower2> bouquet = null;
		
		ResultSet rs = null;
		
		try{
			
			PreparedStatement stmt = conn.prepareStatement(sql.getProperty(SQL_SELECT_BOUQUET_BY_NAME));
			stmt.setString(1, name);
			
			stmt.execute();
			rs = stmt.getResultSet();
			bouquet = getBouquet(rs);
			
		}catch(SQLException se){
			
			se.printStackTrace();
			
		}finally{
			
			try{
				conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
		return bouquet;
	}
	
	private Bouquet2<GeneralFlower2> getBouquet(ResultSet rs){
		
		Bouquet2<GeneralFlower2> bouquet = null;
		
		try{
			
			if(!rs.next()){
				
				return null;
				
			}
			
			bouquet = new BouquetImpl(rs.getInt(1), rs.getString(2));
			((BouquetImpl)bouquet).setPriceDetailed(new Price(rs.getLong(10), rs.getLong(11)));
			GeneralFlower2 flower = null;
			
			do{
				
				flower = new GeneralFlower2();
				flower.setId(rs.getInt(3));
				flower.setName(rs.getString(4));
				flower.setLength(rs.getInt(5));
				flower.setFreshness(new FreshnessInteger(rs.getInt(6)));
				flower.setPriceLong(rs.getLong(7));
				flower.setPetals(rs.getInt(8));
				flower.setSpike(rs.getBoolean(9));
				
				bouquet.addFlower(flower);

			}while(rs.next());
		
		}catch(SQLException se){
			
			se.printStackTrace();			
		}
		
		return bouquet;
	}

	@Override
	public void updateBouquet(int templateId, List<GeneralFlower2> listFlowers) {
		
		Connection conn = dataSource.getConnection();
		
		try{
			
			conn.setAutoCommit(false);
			
			deleteFlowerFromBouquet(conn, templateId);
			
			PreparedStatement stmtIns = null; 
			
			Iterator<GeneralFlower2> it = listFlowers.iterator();
			
			while(it.hasNext()){
				
				stmtIns = conn.prepareStatement(sql.getProperty(SQL_INSERT_BOUQUET));
				stmtIns.setInt(1, templateId);
				stmtIns.setInt(2, it.next().getId());
				stmtIns.addBatch();
				
			}

			stmtIns.executeBatch();
		
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

	}
	
	@Override
	public void deleteBouquet(int templateId) {
		
		Connection conn = dataSource.getConnection();
		
		try{
			
			conn.setAutoCommit(false);
			
			deleteFlowerFromBouquet(conn, templateId);
			
			PreparedStatement stmt = conn.prepareStatement(sql.getProperty(SQL_DELETE_BOUQUET_TEMPLATE));
			
			stmt.setInt(1, templateId);

			stmt.execute();
			
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
	}
	
	private void deleteFlowerFromBouquet(Connection conn, int templateId){
		
		try{
			
			PreparedStatement stmt = conn.prepareStatement(sql.getProperty(SQL_DELETE_FLOWERS_FROM_BOUQUET));
			stmt.setInt(1, templateId);
			
			stmt.execute();
			
		}catch(SQLException se){
			
			se.printStackTrace();			
		}
		
	}

	@Override
	public Price getBouquetPrice(int id) {
		
		Connection conn = dataSource.getConnection();
		ResultSet rs = null;
		Price price = null;

		try{
			
			PreparedStatement stmt = conn.prepareStatement(sql.getProperty(SQL_SELECT_BOUQUET_PRICE_BY_ID));
			stmt.setInt(1, id);			
			stmt.execute();
			rs = stmt.getResultSet();
			
			if(!rs.next()){
				
				return null;
			}
			
			price = new Price(rs.getLong(1), rs.getLong(2));
			
		}catch(SQLException se){
			
			se.printStackTrace();			
		}finally{
			
			try{
				conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
		return price;
	}
	
	@Override
	public Price getBouquetPrice(String name) {

		
		Connection conn = dataSource.getConnection();
		ResultSet rs = null;
		Price price = null;

		try{
			
			PreparedStatement stmt = conn.prepareStatement(sql.getProperty(SQL_SELECT_BOUQUET_PRICE_BY_NAME));
			stmt.setString(1, name);			
			stmt.execute();
			rs = stmt.getResultSet();
			
			if(!rs.next()){
				
				return null;
			}
			
			price = new Price(rs.getLong(1), rs.getLong(2));
			
		}catch(SQLException se){
			
			se.printStackTrace();			
		}finally{
			
			try{
				conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
		return price;
	}

	@Override
	public List<? extends Bouquet<? extends GeneralFlower>> getBouquets() {
		
		Connection conn = dataSource.getConnection();
		ResultSet rs = null;
		List<Bouquet2<GeneralFlower2>> bouquets = null;
		try{
			
			PreparedStatement stmt = conn.prepareStatement(sql.getProperty(SQL_SELECT_BOUQUETS));			
			stmt.execute();
			rs = stmt.getResultSet();
			
			if(!rs.next()){
				
				return null;
			}
			
			bouquets = new ArrayList<>();
			
			BouquetImpl bouquet = null;
			Price price = null;
			
			do{
				
				
				
				bouquet = new BouquetImpl(rs.getInt(1), rs.getString(2));
				price = new Price(rs.getLong(3), rs.getLong(4));
				bouquet.setPriceDetailed(price);
				bouquets.add(bouquet);
				
			}while(rs.next());
			
		}catch(SQLException se){
			
			se.printStackTrace();			
		}finally{
			
			try{
				conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}

		return bouquets;
	}
}
