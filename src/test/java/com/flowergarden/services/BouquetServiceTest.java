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

/**
 * @author SOIERR
 *
 */
@SuppressWarnings("unchecked")
public class BouquetServiceTest {
	
	ApplicationContext ctx = null;
	
	Bouquet2<GeneralFlower2> bouquet = mock(Bouquet2.class);
	
	float samplePrice = 17;
	
	@Before
	public void startSprintContainer(){
		
		ctx = Run.startContainer();
		
		when(bouquet.getPrice()).thenReturn(samplePrice);
	}
	
	@Test
	public void getPriceTest(){
		
		float expectedPrice = 17;
		
		float expectedZeroDelta = 0;
		
		BouquetService bs = ctx.getBean("bouquetService", BouquetService.class);
		
		bs.getPrice(bouquet);
		
		verify(bouquet).getPrice();
		
		Assert.assertEquals(bs.getPrice(bouquet), expectedPrice, expectedZeroDelta);
	}
	
	@Test
	public void getBouquetTest(){
		
		BouquetService bs = ctx.getBean("bouquetService", BouquetService.class);
		Assert.assertNull(bs.getBouquet(3));
		bs = ctx.getBean("bouquetService", BouquetService.class);
		Assert.assertNotNull(bs.getBouquet(1));
		
	}

}
