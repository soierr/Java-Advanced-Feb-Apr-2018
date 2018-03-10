/**
 * 
 */
package com.flowergarden.bouquet;

import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.flowers.GeneralFlower;

/**
 * @author SOIERR
 *
 */
public class GetPriceMockTest {
	
	GeneralFlower flowerMocked = mock(GeneralFlower.class);
	GeneralFlower flowerMocked2 = mock(GeneralFlower.class);
	
	Bouquet<GeneralFlower> bouquetMocked = mock(Bouquet.class);
	
	float assembledPrice = 100;
	
	/*Target Bouquet for testing*/
	Bouquet<GeneralFlower> bouquet = new MarriedBouquet();

	@Before
	public void prepareMock(){
		
		when(flowerMocked.getPrice()).thenReturn(10f);
		when(flowerMocked2.getPrice()).thenReturn(10f);
		
		when(bouquetMocked.getPrice()).thenAnswer(new Answer<Float>() {

			@Override
			public Float answer(InvocationOnMock invocation) throws Throwable {
				
				return flowerMocked.getPrice() + flowerMocked2.getPrice();
			}
			
		});
		
		bouquet.addFlower(flowerMocked);
		bouquet.addFlower(flowerMocked2);
		
		((MarriedBouquet)bouquet).setAssembledPrice(assembledPrice);
	}
	
	@Test
	public void getPriceBouquetTest(){
		
		Assert.assertEquals(bouquet.getPrice(), bouquetMocked.getPrice(), assembledPrice);
	}
}
