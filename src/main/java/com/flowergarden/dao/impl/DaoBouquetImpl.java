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
	
	private String SQL_INSERT_FLOWER = "sqlInsertFlower";
	
	private String SQL_INSERT_BOUQUET = "sqlInsertBouquet";
	private String SQL_SELECT_BOUQUET_BY_ID = "sqlSelectBouquetById";
	private String SQL_SELECT_BOUQUET_BY_NAME = "sqlSelectBouquetByName";
	private String SQL_GET_LAST_ID = "select last_insert_rowid()";
	
	private String SQL_SELECT_BOUQUET_PRICE_BY_ID = "sqlSelectBouquetPriceById";
	private String SQL_SELECT_BOUQUET_PRICE_BY_NAME = "sqlSelectBouquetPriceByName";
	
	private String SQL_SELECT_BOUQUETS = "sqlSelectBouquets";
	
	private String SQL_DELETE_BOUQUET = "sqlDeleteBouquet";
	
	private String SQL_DELETE_FLOWERS_FROM_BOUQUET = "sqlDeleteFlowersFromBouquet";
	
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
	public int create(Bouquet2<GeneralFlower2> bouquet) {
		
		int bouquetId = 0;
		
		Connection conn = null;
		
		List<GeneralFlower2> listFlowers = (List<GeneralFlower2>) bouquet.getFlowers();
		
		try{
			
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement stmtIns = null;
			PreparedStatement stmtSel = null; 
			
			stmtIns = conn.prepareStatement(sql.getProperty(SQL_INSERT_BOUQUET));
			
			stmtIns.setString(1, bouquet.getName());
			stmtIns.setLong(2, bouquet.getPriceDetailed().getPriceAssembling());
			
			stmtSel = conn.prepareStatement(SQL_GET_LAST_ID);
			
			stmtIns.execute();
			stmtSel.execute();
			
			bouquetId = stmtSel.getResultSet().getInt(1);
			
			insertFlowersWoCommit(conn, bouquetId, listFlowers);
			
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
		
		return bouquetId;
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
		
		BouquetImpl bouquet = null;
		
		int bouquetId = 0;
		
		try{
			
			if(!rs.next()){
				
				return null;
				
			}
			
			bouquet = new BouquetImpl(rs.getString(2));
			bouquet.setId((bouquetId = rs.getInt(1)));			
			GeneralFlower2 flower = null;
			
			do{
				
				flower = new GeneralFlower2();
				flower.setId(rs.getInt(4));
				flower.setName(rs.getString(5));
				flower.setLength(rs.getInt(6));
				flower.setFreshness(new FreshnessInteger(rs.getInt(7)));
				flower.setPriceLong(rs.getLong(8));
				flower.setPetals(rs.getInt(9));
				flower.setSpike(rs.getBoolean(10));
				
				bouquet.addFlower(flower);

			}while(rs.next());
			
			bouquet.setPriceDetailed(getPrice(bouquetId));
		
		}catch(SQLException se){
			
			se.printStackTrace();			
		}
		
		return bouquet;
	}

	@Override
	public void update(Bouquet2<GeneralFlower2> bouquet) {
		
		Connection conn = dataSource.getConnection();
		
		List<GeneralFlower2> listFlowers = (List<GeneralFlower2>)bouquet.getFlowers();
		
		int bouquetId = bouquet.getId();
		
		try{
			
			conn.setAutoCommit(false);
			
			deleteFlowersFromBouquet(conn, bouquetId);
			
			insertFlowersWoCommit(conn, bouquetId, listFlowers);
		
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
	
	private void insertFlowersWoCommit(Connection conn, int bouquetId, List<GeneralFlower2> listFlowers) {
		
		try{
			
			
			PreparedStatement stmtIns = conn.prepareStatement(sql.getProperty(SQL_INSERT_FLOWER));
			
			Iterator<GeneralFlower2> it = listFlowers.iterator();
			
			GeneralFlower2 flower = null;
			
			stmtIns = conn.prepareStatement(sql.getProperty(SQL_INSERT_FLOWER));
			
			while(it.hasNext()){
				
				flower = it.next(); 
				
				stmtIns.setString(1, flower.getName());
				stmtIns.setInt(2, flower.getLength());
				stmtIns.setInt(3, flower.getFreshness().getFreshness());
				stmtIns.setLong(4, flower.getPriceLong());
				stmtIns.setInt(5, flower.getPetals());
				stmtIns.setBoolean(6, flower.isSpike());
				stmtIns.setInt(7, bouquetId);
				
				stmtIns.addBatch();
				
			}

			stmtIns.executeBatch();

			
		}catch(SQLException se){
			
			se.printStackTrace();
			
		}

	}
	
	@Override
	public void delete(int bouquetid) {
		
		Connection conn = dataSource.getConnection();
		
		try{
			
			conn.setAutoCommit(false);
			
			deleteFlowersFromBouquet(conn, bouquetid);

			PreparedStatement stmt = conn.prepareStatement(sql.getProperty(SQL_DELETE_BOUQUET));
			
			stmt.setInt(1, bouquetid);

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
	
	private void deleteFlowersFromBouquet(Connection conn, int bouquetId){
		
		try{
			
			PreparedStatement stmt = conn.prepareStatement(sql.getProperty(SQL_DELETE_FLOWERS_FROM_BOUQUET));
			stmt.setInt(1, bouquetId);
			
			stmt.execute();
			
		}catch(SQLException se){
			
			se.printStackTrace();			
		}
		
	}

	@Override
	public Price getPrice(int bouquetId) {
		
		Connection conn = dataSource.getConnection();
		ResultSet rs = null;
		Price price = null;

		try{
			
			PreparedStatement stmt = conn.prepareStatement(sql.getProperty(SQL_SELECT_BOUQUET_PRICE_BY_ID));
			stmt.setInt(1, bouquetId);			
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
	public Price getPrice(String name) {

		
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

				bouquet = new BouquetImpl(rs.getString(2));
				bouquet.setId(rs.getInt(1));
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
