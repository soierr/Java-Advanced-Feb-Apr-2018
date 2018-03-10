/**
 * 
 */
package com.flowergarden.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.flowergarden.dao.impl.DaoFlowerImpl;
import com.flowergarden.flowers.GeneralFlower2;
import com.flowergarden.properties.FreshnessInteger;
import com.flowergarden.rules.CreateDropDbObjectsRule;

import static org.mockito.Mockito.*;

/**
 * @author SOIERR
 *
 */
public class DaoFlowerImplTest {
	
	@Rule
	public CreateDropDbObjectsRule createDropDbObjectsRule = new CreateDropDbObjectsRule();
	
	DaoFlower daoFlower = null;
	
	DaoDataSource dataSource = mock(DaoDataSource.class);
	
	@Before
	public void prepareData() throws Exception{
		
		when(dataSource.getConnection()).thenReturn(createDropDbObjectsRule.getConnection());
				
		daoFlower = new DaoFlowerImpl(dataSource, createDropDbObjectsRule.getSqlScripts());
		
	}
	
	@Test
	public void createFlowerTest(){
		
		GeneralFlower2 flower = new GeneralFlower2();
		flower.setFreshness(new FreshnessInteger(95));
		flower.setLength(95);
		flower.setName("Chamomile");
		flower.setPetals(15);
		flower.setPriceLong(10000L);
		flower.setSpike(false);
		
		Assert.assertTrue(daoFlower.create(flower) != 0);
	}
	
	@Test
	public void getFlowersTest(){
		
		List<GeneralFlower2> listFlowers = daoFlower.getFlowers();
		
		Assert.assertTrue(listFlowers.size() > 0);
	}

}
