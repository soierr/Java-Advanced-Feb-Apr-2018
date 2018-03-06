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

import com.flowergarden.flowers.restricted.Carnation;
import com.flowergarden.flowers.restricted.FlowerRestrictedColor;
import com.flowergarden.flowers.restricted.RestrictedByColor;

/**
 * @author SOIERR
 *
 */
/*Here we make shallow testing just to make sure that we have proper new implementation*/
@RunWith(Parameterized.class)
public class BouquetRestrictedColorTest {
	
	BouquetRestrictedColor<RestrictedByColor, FlowerRestrictedColor.COLORS> bouquet = null;	
	Set<FlowerRestrictedColor.COLORS> colorSet = null;
	RestrictedByColor flower = null;
	
	/**
	 * 
	 */
	
	public BouquetRestrictedColorTest(Set<FlowerRestrictedColor.COLORS> es, RestrictedByColor flower) {
		
		this.colorSet = es;
		this.bouquet = new BouquetRestrictedColorImpl(es);
		this.flower = flower;
	}
	
	
	@Test
	public void putFlowerSuccessfulTest(){

		Assert.assertTrue(bouquet.put(new Carnation()));
		
	}
	
	@Test
	public void setGetColorSetTest(){

		bouquet.setColorSet(colorSet);
		Assert.assertTrue(colorSet == bouquet.getColorSet());
		
	}
	
	
	@Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
    	
    	/*Here we just demonstrate how to client can store different color sets.*/
    	Map<String, Set<FlowerRestrictedColor.COLORS>> mapBouquetColorSets = new HashMap<>();   	
    	mapBouquetColorSets.put("onlyRed", EnumSet.of(FlowerRestrictedColor.COLORS.RED));
		mapBouquetColorSets.put("mixRedWhite", EnumSet.of(FlowerRestrictedColor.COLORS.RED, 
													   FlowerRestrictedColor.COLORS.WHITE));
        
    	return Arrays.asList(new Object[]{mapBouquetColorSets.get("onlyRed"), new Carnation()}, 
    				 		 new Object[]{mapBouquetColorSets.get("mixRedWhite"), new Carnation()});
    }

}
