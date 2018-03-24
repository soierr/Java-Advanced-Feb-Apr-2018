/**
 * 
 */
package com.flowergarden.dao;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.flowergarden.dao.impl.DaoFlowerImpl;
import com.flowergarden.flowers.GeneralFlower2;
import com.flowergarden.properties.FreshnessInteger;
import com.flowergarden.rules.CreateDropDbObjectsRule;

import static org.mockito.Mockito.*;

/**
 * @author SOIERR
 *
 */
public class DaoFlowerTest {
	
	@Rule
	public CreateDropDbObjectsRule createDropDbObjectsRule = new CreateDropDbObjectsRule();
	
	DaoFlower daoFlower = null;
	
	DaoDataSource dataSource = mock(DaoDataSource.class);
	
	@Before
	public void prepareData() throws Exception{
		
		when(dataSource.getConnection()).then(new Answer<Connection>() {
			
			@Override
			public Connection answer(InvocationOnMock invocation) throws Throwable {

				return createDropDbObjectsRule.getConnection();
			}
		});
				
		daoFlower = new DaoFlowerImpl(dataSource, createDropDbObjectsRule.getSqlScripts());
		
	}
	
	@Test
	public void createTest(){
		
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
	public void getFlowerTest(){
		
		GeneralFlower2 flower = daoFlower.getFlower(1);
		
		Assert.assertTrue(flower.getId() == 1);
	}
	
	@Test
	public void updateTest(){
		
		int lengthOriginal = 95;		
		int lengthUpdated = 90;
		
		int petalsOriginal = 15;
		int petalsUpdated = 17;
		
		GeneralFlower2 chamomileUpdated = null;
		
		GeneralFlower2 chamomile = new GeneralFlower2();
		chamomile.setFreshness(new FreshnessInteger(95));
		chamomile.setLength(lengthOriginal);
		chamomile.setName("Chamomile");
		chamomile.setPetals(petalsOriginal);
		chamomile.setPriceLong(100L);
		chamomile.setSpike(false);
		
		int flowerId = daoFlower.create(chamomile);
		
		Assert.assertTrue(flowerId != 0);
		
		chamomile.setId(flowerId);
		
		chamomile.setLength(lengthUpdated);
		chamomile.setPetals(petalsUpdated);
		
		daoFlower.update(chamomile);
		
		chamomileUpdated = daoFlower.getFlower(chamomile.getId());
		
		Assert.assertTrue(chamomileUpdated.getLength() == lengthUpdated);
		Assert.assertTrue(chamomileUpdated.getPetals() == petalsUpdated);
	}
	
	@Test
	public void deleteTest(){
		
		Assert.assertTrue(daoFlower.getFlower(1) != null);
		
		daoFlower.delete(1);
		
		Assert.assertNull(daoFlower.getFlower(1));
	}

}
