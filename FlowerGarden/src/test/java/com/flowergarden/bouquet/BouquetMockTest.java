/**
 * 
 */
package com.flowergarden.bouquet;

import static org.mockito.Mockito.*;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.flowers.Tulip;

/**
 * @author SOIERR
 *
 */
public class BouquetMockTest {
	
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
	
	@Test
	public void searchFlowersByLenghtTest(){
		
		Rose roseMocked = mock(Rose.class);
		Tulip tulipMocked = mock(Tulip.class);
		Chamomile chamomileMocked = mock(Chamomile.class);
		
		int inRangeLength = 150;
		int outRangeLength = 50;
		int expectedNumFlowersSearched = 2;
		
		Bouquet<GeneralFlower> bouquet = new MarriedBouquet();
		
		when(roseMocked.getLenght()).thenReturn(inRangeLength);
		when(tulipMocked.getLenght()).thenReturn(inRangeLength);
		when(chamomileMocked.getLenght()).thenReturn(outRangeLength);
		
		bouquet.addFlower(roseMocked);
		bouquet.addFlower(tulipMocked);
		bouquet.addFlower(chamomileMocked);
		
		Collection<GeneralFlower> col = bouquet.searchFlowersByLenght(inRangeLength, inRangeLength);
		
		Assert.assertEquals(col.size(), expectedNumFlowersSearched, 0);
	}
}
