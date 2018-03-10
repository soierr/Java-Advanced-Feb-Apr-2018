/**
 * 
 */
package com.flowergarden.properties;

import org.junit.Before;
import org.junit.Test;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.flowers.GeneralFlower;

/**
 * @author SOIERR
 *
 */
public class SortByFreshnessNPETest {
	
		private Bouquet<GeneralFlower> bouquet = null;
	
	@Before
	public void prepareBouquet(){
		
		bouquet = new MarriedBouquet();
		
		bouquet.addFlower(new GeneralFlower());
		bouquet.addFlower(new GeneralFlower());
		
	}
	
	@Test(expected=NullPointerException.class)
	public void sortByFreshnessNPETest(){

		bouquet.sortByFreshness();
	}

}
