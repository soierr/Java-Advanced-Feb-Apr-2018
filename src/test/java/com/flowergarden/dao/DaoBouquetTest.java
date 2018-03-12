/**
 * 
 */
package com.flowergarden.dao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.Bouquet2;
import com.flowergarden.bouquet.Price;
import com.flowergarden.dao.impl.DaoBouquetImpl;
import com.flowergarden.dao.impl.DaoBouquetTemplateImpl;
import com.flowergarden.dao.model.BouquetImpl;
import com.flowergarden.dao.model.BouquetTemplate;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.GeneralFlower2;

import com.flowergarden.properties.FreshnessInteger;
import com.flowergarden.rules.CreateDropDbObjectsRule;

/**
 * @author SOIERR
 *
 */
@SuppressWarnings("unchecked")
public class DaoBouquetTest {
	
	@Rule
	public CreateDropDbObjectsRule createDropDbObjectsRule = new CreateDropDbObjectsRule();
	
	DaoBouquet daoBouquet = null;
	
	DaoBouquetTemplate daoBouquetTemplate = null;
	
	DaoDataSource dataSource = mock(DaoDataSource.class);
	
	@Before
	public void prepareData() throws Exception{
		
		/*if thenReturn then Connection is getting only once */
		when(dataSource.getConnection()).then(new Answer<Connection>() {
			
			@Override
			public Connection answer(InvocationOnMock invocation) throws Throwable {

				return createDropDbObjectsRule.getConnection();
			}
		});
		
		daoBouquet = new DaoBouquetImpl(dataSource, createDropDbObjectsRule.getSqlScripts());
		daoBouquetTemplate = new DaoBouquetTemplateImpl(dataSource, createDropDbObjectsRule.getSqlScripts()); 
		
	}
	
	@Test
	public void createBouquetTest(){
		
		BouquetTemplate bt = new BouquetTemplate();
		bt.setName("First Date");
		bt.setPriceAssembling(150);
		
		int templateId = daoBouquetTemplate.createBouquetTemplate(bt);
		
		GeneralFlower2 rose = new GeneralFlower2();
		rose.setFreshness(new FreshnessInteger(90));
		rose.setSpike(true);
		rose.setLength(160);
		rose.setName("Rose");
		rose.setPriceLong(75L);
		
		GeneralFlower2 carnation = new GeneralFlower2();
		carnation.setFreshness(new FreshnessInteger(90));
		carnation.setSpike(true);
		carnation.setLength(160);
		carnation.setName("Carnation");
		carnation.setPriceLong(75L);
		
		GeneralFlower2 tulip = new GeneralFlower2();
		tulip.setFreshness(new FreshnessInteger(90));
		tulip.setSpike(true);
		tulip.setLength(160);
		tulip.setName("Tulip");
		tulip.setPriceLong(75L);
		
		List<GeneralFlower2> listFlowers = new ArrayList<GeneralFlower2>();
		listFlowers.add(rose);
		listFlowers.add(carnation);
		listFlowers.add(tulip);
				
		int bouquetId = daoBouquet.createBouquet(templateId, listFlowers);
		
		Assert.assertTrue(bouquetId > 0);
	}

	@Test
	public void getBouquetByIdTest(){
		
		Bouquet2<GeneralFlower2> bouquet = (Bouquet2<GeneralFlower2>) daoBouquet.getBouquet(1);
		
		Bouquet<GeneralFlower> bouquetOld = (Bouquet<GeneralFlower>) daoBouquet.getBouquet(1);
		
		Assert.assertTrue("First Date".equals(bouquet.getName()));
		Assert.assertTrue(bouquetOld.getPrice() != 0);
		Assert.assertTrue(bouquetOld.getPrice() == bouquet.getPriceDetailed().getPriceTotal()/100);
		
	}

	@Test
	public void getBouquetByNameTest(){
		
		Bouquet2<GeneralFlower2> bouquet = (Bouquet2<GeneralFlower2>) daoBouquet.getBouquet("First Date");
		
		Bouquet<GeneralFlower> bouquetOld = (Bouquet<GeneralFlower>) daoBouquet.getBouquet("First Date");
		
		Assert.assertTrue("First Date".equals(bouquet.getName()));
		Assert.assertTrue(bouquetOld.getPrice() != 0);
		Assert.assertTrue(bouquetOld.getPrice() == bouquet.getPriceDetailed().getPriceTotal()/100);
		
	}
	
	@Test
	public void updateBouquetTest(){
		
		Bouquet2<GeneralFlower2> bouquet = (Bouquet2<GeneralFlower2>) daoBouquet.getBouquet("First Date");
		
		Collection<GeneralFlower2> listFlowers = bouquet.getFlowers();
		Iterator<GeneralFlower2> it = listFlowers.iterator();
		
		it.next();
		it.remove();
		it.next();
		it.remove();
		
		daoBouquet.updateBouquet(((BouquetImpl)bouquet).getId(), (List<GeneralFlower2>)listFlowers);
		
		Assert.assertTrue(bouquet.getFlowers().size() == 1);
	}
	
	@Test
	public void deleteBouquetTest(){
		
		Bouquet2<GeneralFlower2> bouquet = (Bouquet2<GeneralFlower2>) daoBouquet.getBouquet("First Date");
		
		int bouquetId = ((BouquetImpl)bouquet).getId();
		
		daoBouquet.deleteBouquet(bouquetId);

		Assert.assertNull(daoBouquet.getBouquet("First Date"));		
	}
	
	@Test
	public void getBouquetPriceByIdTest(){
		
		Price price = daoBouquet.getBouquetPrice(1);
		
		Assert.assertTrue(price.getPriceAssembling() > 0);
		Assert.assertTrue(price.getPriceTotal() > 0);
	}
	
	@Test
	public void getBouquetPriceByNameTest(){
		
		Price price = daoBouquet.getBouquetPrice("Wedding");
		
		Assert.assertTrue(price.getPriceAssembling() > 0);
		Assert.assertTrue(price.getPriceTotal() > 0);		
	}
	
	@Test
	public void getBouquetsTest(){
		
		List<Bouquet2<GeneralFlower2>> bouquets = (List<Bouquet2<GeneralFlower2>>) daoBouquet.getBouquets();
		
		Assert.assertTrue(bouquets.get(0).getName().equals("First Date"));
	}
	
}
