/**
 * 
 */
package com.flowergarden.bouquet.restricted;

import java.util.EnumSet;


import org.junit.Assert;
import org.junit.Test;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.flowers.restricted.FlowerRestrictedColor;
import com.flowergarden.flowers.restricted.RestrictedByColor;
import com.flowergarden.flowers.restricted.FlowerRestrictedColor.COLORS;

/**
 * @author SOIERR
 *
 */
/*Here we test the following ideas:
 * 1. GeneralFlower with no RestrictedByColor implementation
 * cannot be added through the method of Bouquet 
 * 2. RestrictedByColor cannot be put through the BouquetRestrictedColor
 * if it's not a GeneralFlower*/
	
public class BouquetsCompatibilityTest {
	
	Bouquet<GeneralFlower> bouquetRestrictedColor = new BouquetRestrictedColorImpl(EnumSet.of(FlowerRestrictedColor.COLORS.RED));
	
	public BouquetsCompatibilityTest() {
		
		
	}
	
	@Test(expected=ClassCastException.class)
	public void checkBouquetRestictedAddFlowerCCException(){
		
		bouquetRestrictedColor.addFlower(new Rose()); 
	}
	
	@Test
	public void checkBouquetRestictedAddFlowerCCExceptionText(){
		
		try{
			bouquetRestrictedColor.addFlower(new Rose());
		}catch(ClassCastException cce){
			
			Assert.assertEquals(cce.getMessage(), "Wrong method called. Use proper flower implementation");
		}
	}
	
	@Test(expected=ClassCastException.class)
	public void checkBouquetRestictedPutCCException(){
		
		BouquetRestrictedColor<RestrictedByColor, FlowerRestrictedColor.COLORS> bouquetRes =  
				(BouquetRestrictedColorImpl) bouquetRestrictedColor;
		
		/*Here RestrictedByColor is not a general flower*/
		bouquetRes.put(new RestrictedByColor() {
			
			@Override
			public COLORS getParameter() {
				
				return FlowerRestrictedColor.COLORS.RED;
			}
		});
	}

}


