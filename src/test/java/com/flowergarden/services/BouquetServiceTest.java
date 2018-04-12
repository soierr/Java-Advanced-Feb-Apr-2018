/**
 * 
 */
package com.flowergarden.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.flowergarden.bouquet.Bouquet2;
import com.flowergarden.flowers.GeneralFlower2;
import com.flowergarden.run.Run;

import static org.mockito.Mockito.*;

import java.util.List;

/**
 * @author SOIERR
 *
 */
@SuppressWarnings("unchecked")
public class BouquetServiceTest {
	
	ApplicationContext ctx = null;
	
	Bouquet2<GeneralFlower2> bouquet = mock(Bouquet2.class);
	
	//float samplePrice = 17f;
	
	int sampleBouquetId = 1;
	
	@Before
	public void startSprintContainer(){
		
		ctx = Run.startContainer();
		
		//when(bouquet.getPrice()).thenReturn(samplePrice);
	}
	
/*	@Test
 *	//TODO Refactor it
	public void getPriceTest(){
		
		float expectedPrice = 17;
		
		float expectedZeroDelta = 0;
		
		int sampleBouquetId = 1;
		
		BouquetService bs = ctx.getBean("bouquetService", BouquetService.class);
		
		bs.getPrice(sampleBouquetId);
		
		Assert.assertEquals(bs.getPrice(sampleBouquetId), expectedPrice, expectedZeroDelta);
	}*/
	
	@Test
	public void getBouquetTest(){
		
		BouquetService bs = ctx.getBean("bouquetService", BouquetService.class);
		Assert.assertNull(bs.getBouquet(3));
		bs = ctx.getBean("bouquetService", BouquetService.class);
		Assert.assertNotNull(bs.getBouquet(1));
		
	}
	
	@Test
	public void decrementFreshnessTest(){
		
		BouquetService bs = ctx.getBean("bouquetService", BouquetService.class);
		Bouquet2<GeneralFlower2> bouquet = (Bouquet2<GeneralFlower2>) bs.getBouquet(sampleBouquetId);
		
		int initialFreshnessTotal = 0;
		
		List<GeneralFlower2> listFlowers = (List<GeneralFlower2>) bouquet.getFlowers();
		
		for(GeneralFlower2 flower : listFlowers){
			
			initialFreshnessTotal += flower.getFreshness().getFreshness();
			//flower.setFreshness(new FreshnessInteger(flower.getFreshness().getFreshness()-1));
		}
		
		/*we assume that all current sample flowers has freshness > 0*/		
		List<GeneralFlower2> lisrFlowerZeroPrice = bs.decrementFreshness(bouquet.getId()).getListFlowersZeroPrice();
		
		
		Assert.assertTrue(lisrFlowerZeroPrice.isEmpty());
		
		int changedFreshnessTotal = 0;
		
		bouquet = (Bouquet2<GeneralFlower2>) bs.getBouquet(bouquet.getId());
		
		listFlowers = (List<GeneralFlower2>) bouquet.getFlowers();
		
		for(GeneralFlower2 flower : listFlowers){
			
			changedFreshnessTotal += flower.getFreshness().getFreshness();
		}
		
		/*as requirement states we need decrement freshness of every flowers in bouquet*/
		Assert.assertTrue(initialFreshnessTotal == changedFreshnessTotal + listFlowers.size());;
	}
	

}
