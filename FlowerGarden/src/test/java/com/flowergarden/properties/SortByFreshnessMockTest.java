/**
 * 
 */
package com.flowergarden.properties;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.flowers.Tulip;

import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author SOIERR
 *
 */
public class SortByFreshnessMockTest {
	
	Rose roseMocked = mock(Rose.class);
	Tulip tulipMocked = mock(Tulip.class);
	
	FreshnessInteger freshnessRoseMocked = mock(FreshnessInteger.class);
	FreshnessInteger freshnessTulipMocked = mock(FreshnessInteger.class);
	
	Bouquet<GeneralFlower> bouquet = new MarriedBouquet();
	
	@Before
	public void prepare(){
		
		when(freshnessRoseMocked.getFreshness()).thenReturn(95);
		when(freshnessTulipMocked.getFreshness()).thenReturn(90);
		
		when(roseMocked.getFreshness()).thenReturn(freshnessRoseMocked);
		when(tulipMocked.getFreshness()).thenReturn(freshnessTulipMocked);
		
		when(roseMocked.compareTo(any(GeneralFlower.class))).thenCallRealMethod();
		when(tulipMocked.compareTo(any(GeneralFlower.class))).thenCallRealMethod();
		
		bouquet.addFlower(roseMocked);
		bouquet.addFlower(tulipMocked);
		
	}
	
	@Test
	public void sortByFreshnessMockTest(){
		
		bouquet.sortByFreshness();
		
		/*Check object identity*/
		Assert.assertTrue(bouquet.getFlowers().iterator().next() == tulipMocked);
	}

}
