/**
 * 
 */
package com.flowergarden.bouquet.restricted;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.flowergarden.flowers.Rose;
import com.flowergarden.flowers.restricted.Carnation;
import com.flowergarden.flowers.restricted.FlowerRestrictedColor;
import com.flowergarden.flowers.restricted.RestrictedByColor;

/**
 * @author SOIERR
 *
 */
@RunWith(Parameterized.class)
public class BouquetRestrictedColorImplTest {
	
	BouquetRestrictedColorImpl bouquet = null;
	RestrictedByColor flower = null;
	boolean expectedPutResult = false;
	/**
	 * 
	 */
	
	public BouquetRestrictedColorImplTest(Set<FlowerRestrictedColor.COLORS> es, RestrictedByColor flower, boolean expectedPutResult) {
		
		bouquet = new BouquetRestrictedColorImpl(es);
		this.flower = flower;
		this.expectedPutResult = expectedPutResult;
	}
	
	
	@Test
	public void putFlowerSuccessfulAndFailedTest(){

		Assert.assertEquals(bouquet.put(flower), expectedPutResult);
	}
	
	@Test(expected=ClassCastException.class)
	public void addFlowerExceptionTest(){
		
		bouquet.addFlower(new Rose());
	}
	
	@Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
    	
    	/*Here we just demonstrate how to client can store different color sets.*/
    	Map<String, Set<FlowerRestrictedColor.COLORS>> mapBouquetColorSets = new HashMap<>();   	
    	mapBouquetColorSets.put("onlyRed", EnumSet.of(FlowerRestrictedColor.COLORS.RED));
		mapBouquetColorSets.put("mixRedWhite", EnumSet.of(FlowerRestrictedColor.COLORS.RED, 
													   FlowerRestrictedColor.COLORS.WHITE));
		mapBouquetColorSets.put("onlyWhite", EnumSet.of(FlowerRestrictedColor.COLORS.WHITE));
        
    	return Arrays.asList(new Object[]{mapBouquetColorSets.get("onlyRed"), new Carnation(), true}, 
    				 		 new Object[]{mapBouquetColorSets.get("mixRedWhite"), new Carnation(), true},
    				 		 new Object[]{mapBouquetColorSets.get("onlyWhite"), new Carnation(), false});
    }

}
