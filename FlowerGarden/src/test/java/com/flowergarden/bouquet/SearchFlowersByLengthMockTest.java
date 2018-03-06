/**
 * 
 */
package com.flowergarden.bouquet;

import static org.mockito.Mockito.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.flowers.Tulip;

import org.junit.Assert;

/**
 * @author SOIERR
 *
 */
public class SearchFlowersByLengthMockTest {
	
	Rose roseMocked = mock(Rose.class);
	Tulip tulipMocked = mock(Tulip.class);
	Chamomile chamomileMocked = mock(Chamomile.class);
	
	int inRangeLength = 150;
	int outRangeLength = 50;
	int expectedNumFlowersSearched = 2;
	
	Bouquet<GeneralFlower> bouquet = new MarriedBouquet();
	
	@Before
	public void prepareBouquet(){
		
		when(roseMocked.getLenght()).thenReturn(inRangeLength);
		when(tulipMocked.getLenght()).thenReturn(inRangeLength);
		when(chamomileMocked.getLenght()).thenReturn(outRangeLength);
		
		bouquet.addFlower(roseMocked);
		bouquet.addFlower(tulipMocked);
		bouquet.addFlower(chamomileMocked);
	}
	
	@Test
	public void searchFlowersByLenghtTest(){
		
		Collection<GeneralFlower> col = bouquet.searchFlowersByLenght(inRangeLength, inRangeLength);
		
		Assert.assertEquals(col.size(), expectedNumFlowersSearched, 0);
	}
	
}
