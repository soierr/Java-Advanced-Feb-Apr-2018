/**
 * 
 */
package com.flowergarden.dao;

import java.util.List;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.Bouquet2;
import com.flowergarden.bouquet.Price;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.GeneralFlower2;

/**
 * @author SOIERR
 *
 */
public interface DaoBouquet {
	
	public int create(Bouquet2<GeneralFlower2> bouquet);
	
	public Bouquet<? extends GeneralFlower> getBouquet(int id);
	
	public Bouquet<? extends GeneralFlower> getBouquet(String bouquetName);
	
	/*the only flowers might be replaced with those set in input bouquet*/
	public void update(Bouquet2<GeneralFlower2> bouquet);
	
	public void delete(int bouquetId);
	
	public Price getPrice(int bouquetId);
	
	public Price getPrice(String bouquetName);
	
	public List<? extends Bouquet<? extends GeneralFlower>> getBouquets();

}
