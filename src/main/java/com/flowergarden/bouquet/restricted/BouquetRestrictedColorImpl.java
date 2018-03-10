/**
 * 
 */
package com.flowergarden.bouquet.restricted;

import java.util.Set;

import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.restricted.FlowerRestrictedColor;
import com.flowergarden.flowers.restricted.RestrictedByColor;

/**
 * @author SOIERR
 *
 */
public class BouquetRestrictedColorImpl extends MarriedBouquet implements BouquetRestrictedColor<RestrictedByColor, FlowerRestrictedColor.COLORS>{
	
	Set<FlowerRestrictedColor.COLORS> colorSet;
	
	/**
	 * 
	 */
	public BouquetRestrictedColorImpl(Set<FlowerRestrictedColor.COLORS> colorSet) {
		
		this.colorSet = colorSet;
	}
	
	/* (non-Javadoc)
	 * @see com.flowergarden.bouquet.restricted.BouquetRestrictedColor#addFlower(java.lang.Object)
	 */
	@Override
	public boolean put(RestrictedByColor flower){
		
		if(!colorSet.contains(flower.getParameter())){

			return false;			
		}
		
		super.addFlower((GeneralFlower)flower);
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.flowergarden.bouquet.MarriedBouquet#addFlower(com.flowergarden.flowers.GeneralFlower)
	 */
	@Override
	public void addFlower(GeneralFlower flower) {
		
		try{
			put((RestrictedByColor)flower);
		}catch(ClassCastException cce){
			
			throw new ClassCastException("Wrong method called. Use proper flower implementation");
		}
	}
	
	/**
	 * @param colorSet the colorSet to set
	 */
	public void setColorSet(Set<FlowerRestrictedColor.COLORS> colorSet) {
		this.colorSet = colorSet;
	}
	
	/**
	 * @return the colorSet
	 */
	public Set<FlowerRestrictedColor.COLORS> getColorSet() {
		return colorSet;
	}
	
}
