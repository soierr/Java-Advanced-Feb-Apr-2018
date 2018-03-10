/**
 * 
 */
package com.flowergarden.dao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.flowergarden.dao.impl.DaoBouquetTemplateImpl;
import com.flowergarden.dao.model.BouquetTemplate;
import com.flowergarden.rules.CreateDropDbObjectsRule;

/**
 * @author SOIERR
 *
 */
public class DaoBouquetTemplateTest {
	
	@Rule
	public CreateDropDbObjectsRule createDropDbObjectsRule = new CreateDropDbObjectsRule();
	
	DaoBouquetTemplate daoBouquetTemplate = null;
	
	DaoDataSource dataSource = mock(DaoDataSource.class);
	
	int bouquetId = 0;
	
	BouquetTemplate bouquetTemplate = null;
	
	@Before
	public void prepareData() throws Exception{
		
		when(dataSource.getConnection()).thenReturn(createDropDbObjectsRule.getConnection());
		
		/*if thenReturn then Connection is getting only once */
		when(dataSource.getConnection()).then(new Answer<Connection>() {
		
			@Override
			public Connection answer(InvocationOnMock invocation) throws Throwable {
			
				return createDropDbObjectsRule.getConnection();
			}
		});
		
		daoBouquetTemplate = new DaoBouquetTemplateImpl(dataSource, createDropDbObjectsRule.getSqlScripts());
		
		bouquetTemplate = new BouquetTemplate();
		bouquetTemplate.setName("First Date");
		bouquetTemplate.setPriceAssembling(150);
		
	}
	
	@Test
	public void createBouquetTemplateTest(){
		
		bouquetId = daoBouquetTemplate.createBouquetTemplate(bouquetTemplate);
		
		Assert.assertTrue(bouquetId > 0);
	}
	
	@Test
	public void updateBouquetTemplate(){
		
		bouquetTemplate.setName("Second Date");
		daoBouquetTemplate.updateBouquetTemplate(bouquetTemplate);
		
		Assert.assertTrue("Second Date".equals(bouquetTemplate.getName()));
	}
	
	@Test
	public void deleteBouquetTemplate(){

		daoBouquetTemplate.deleteBouquetTemplate(bouquetTemplate.getId());
	}

}
