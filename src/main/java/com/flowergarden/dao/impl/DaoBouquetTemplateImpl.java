/**
 * 
 */
package com.flowergarden.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.flowergarden.dao.DaoBouquetTemplate;
import com.flowergarden.dao.DaoDataSource;
import com.flowergarden.dao.model.BouquetTemplate;

/**
 * @author SOIERR
 *
 */
public class DaoBouquetTemplateImpl implements DaoBouquetTemplate{
	
	
	private String SQL_INSERT_BOUQUET_TEMPLATE = "sqlInsertBouquetTemplate";
	private String SQL_UPDATE_BOUQUET_TEMPLATE = "sqlUpdateBouquetTemplate";
	private String SQL_DELETE_BOUQUET_TEMPLATE = "sqlDeleteBouquetTemplate";
	private String SQL_GET_LAST_ID = "select last_insert_rowid()";
	
	private DaoDataSource dataSource = null;
	
	private Properties sql = null;
	
	/**
	 * 
	 */
	public DaoBouquetTemplateImpl(DaoDataSource dataSource, Properties sql) {
		
		this.dataSource = dataSource;
		this.sql = sql;
	}
	
	
	@Override
	public int createBouquetTemplate(BouquetTemplate template) {
		
		int createdId = 0;
		
		Connection conn = null;
		
		try{
			
			conn = dataSource.getConnection();
			
			PreparedStatement stmtIns = conn.prepareStatement(sql.getProperty(SQL_INSERT_BOUQUET_TEMPLATE));
			PreparedStatement stmtSel = conn.prepareStatement(SQL_GET_LAST_ID);
			
			stmtIns.setString(1, template.getName());
			stmtIns.setLong(2, template.getPriceAssembling());
						
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
	public void updateBouquetTemplate(BouquetTemplate template) {
		
		Connection conn = null;
		
		try{
			
			conn = dataSource.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(sql.getProperty(SQL_UPDATE_BOUQUET_TEMPLATE));
			
			stmt.setInt(1, template.getId());
			stmt.setString(2, template.getName());
			stmt.setLong(3, template.getPriceAssembling());

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
	public void deleteBouquetTemplate(int templateId) {
		
		Connection conn = null;
		
		try{
			
			conn = dataSource.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(sql.getProperty(SQL_DELETE_BOUQUET_TEMPLATE));
			
			stmt.setInt(1, templateId);

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

}
