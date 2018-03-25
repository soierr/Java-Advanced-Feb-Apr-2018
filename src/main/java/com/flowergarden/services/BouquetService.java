/**
 * 
 */
package com.flowergarden.services;

import org.springframework.stereotype.Service;

import com.flowergarden.bouquet.Bouquet2;
import com.flowergarden.flowers.GeneralFlower2;

/**
 * @author SOIERR
 *
 */
@Service("bouquetService")
public class BouquetService {
	
	public float getPrice(Bouquet2<GeneralFlower2> bouquet){
		
		return bouquet.getPrice();
	}

}
