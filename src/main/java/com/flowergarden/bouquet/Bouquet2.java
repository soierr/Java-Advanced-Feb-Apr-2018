/**
 * 
 */
package com.flowergarden.bouquet;

import com.flowergarden.flowers.GeneralFlower2;

/**
 * @author SOIERR
 *
 */
public interface Bouquet2<T extends GeneralFlower2> extends Bouquet<T>{

	//TODO Move comments
	/*This method is supposed to replace getPrice with float return type*/
	public long getPriceTotal();	
	public long getPriceAssembling();
	public String getName();
	
}