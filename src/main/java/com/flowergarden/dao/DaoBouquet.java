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
	
	int create(Bouquet2<GeneralFlower2> bouquet);
	
	Bouquet<? extends GeneralFlower> getBouquet(int id);
	
	Bouquet<? extends GeneralFlower> getBouquet(String bouquetName);
	
	/*the only flowers might be replaced with those set in input bouquet; i.e. delete then insert*/
	void update(Bouquet2<GeneralFlower2> bouquet);
	
	/*replace ddl used: i.e update or/and insert*/
	void updateAndAdd(Bouquet2<GeneralFlower2> bouquet);
	
	void delete(int bouquetId);
	
	Price getPrice(int bouquetId);
	
	Price getPrice(String bouquetName);
	
	List<? extends Bouquet<? extends GeneralFlower>> getBouquets();

}
