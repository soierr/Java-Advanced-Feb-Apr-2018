/**
 * 
 */
package com.flowergarden.bouquet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import com.flowergarden.properties.FreshnessInteger;

/**
 * @author SOIERR
 *
 */
@SuppressWarnings("unchecked")
public class BouquetMockTest {
	
	Bouquet<GeneralFlower> bouquetMocked = mock(Bouquet.class);
	
	/*Target Bouquet for testing*/
	Bouquet<GeneralFlower> bouquetTarget = null;

	@Before
	public void createBouquet(){
		
		bouquetTarget = new MarriedBouquet();
	}
	
	@Test
	public void addFlowerBouquetTest(){
		
		Rose roseMocked = mock(Rose.class);		
		Chamomile chamomileMocked = mock(Chamomile.class);

		List<GeneralFlower> genFlowerList = mock(ArrayList.class);
		
		((MarriedBouquet)bouquetTarget).setFlowerListTest(genFlowerList);
		
		bouquetTarget.addFlower(roseMocked);
		bouquetTarget.addFlower(chamomileMocked);
		
		verify(genFlowerList).add(roseMocked);
		verify(genFlowerList, times(2)).add(any(GeneralFlower.class));
	}
	
	@Test
	public void getPriceBouquetTest(){
		
		GeneralFlower flowerMocked = mock(GeneralFlower.class);
		GeneralFlower flowerMocked2 = mock(GeneralFlower.class);
		
		float assembledPrice = 100;
		
		when(flowerMocked.getPrice()).thenReturn(10f);
		when(flowerMocked2.getPrice()).thenReturn(10f);
		
		when(bouquetMocked.getPrice()).thenAnswer(new Answer<Float>() {

			@Override
			public Float answer(InvocationOnMock invocation) throws Throwable {
				
				return flowerMocked.getPrice() + flowerMocked2.getPrice();
			}
			
		});
		
		bouquetTarget.addFlower(flowerMocked);
		bouquetTarget.addFlower(flowerMocked2);
		
		((MarriedBouquet)bouquetTarget).setAssembledPrice(assembledPrice);
		
		Assert.assertEquals(bouquetTarget.getPrice(), bouquetMocked.getPrice(), assembledPrice);
	}
	
	@Test
	public void searchFlowersByLenghtBouquetTest(){
		
		Rose roseMocked = mock(Rose.class);
		Tulip tulipMocked = mock(Tulip.class);
		Chamomile chamomileMocked = mock(Chamomile.class);
		
		int inRangeLength = 150;
		int outRangeLength = 50;
		int expectedNumFlowersSearched = 2;

		
		when(roseMocked.getLenght()).thenReturn(inRangeLength);
		when(tulipMocked.getLenght()).thenReturn(inRangeLength);
		when(chamomileMocked.getLenght()).thenReturn(outRangeLength);
		
		bouquetTarget.addFlower(roseMocked);
		bouquetTarget.addFlower(tulipMocked);
		bouquetTarget.addFlower(chamomileMocked);
		
		Collection<GeneralFlower> col = bouquetTarget.searchFlowersByLenght(inRangeLength, inRangeLength);
		
		Assert.assertEquals(col.size(), expectedNumFlowersSearched, 0);
	}
	
	@Test
	public void sortByFreshnessMockBouquetTest(){
		
		Rose roseMocked = mock(Rose.class);
		Tulip tulipMocked = mock(Tulip.class);
		
		FreshnessInteger freshnessRoseMocked = mock(FreshnessInteger.class);
		FreshnessInteger freshnessTulipMocked = mock(FreshnessInteger.class);
		
		when(freshnessRoseMocked.getFreshness()).thenReturn(95);
		when(freshnessTulipMocked.getFreshness()).thenReturn(90);
		
		when(roseMocked.getFreshness()).thenReturn(freshnessRoseMocked);
		when(tulipMocked.getFreshness()).thenReturn(freshnessTulipMocked);
		
		when(roseMocked.compareTo(any(GeneralFlower.class))).thenCallRealMethod();
		when(tulipMocked.compareTo(any(GeneralFlower.class))).thenCallRealMethod();
		
		bouquetTarget.addFlower(roseMocked);
		bouquetTarget.addFlower(tulipMocked);
		
		bouquetTarget.sortByFreshness();
		
		/*Check object identity*/
		Assert.assertTrue(bouquetTarget.getFlowers().iterator().next() == tulipMocked);
	}
}
