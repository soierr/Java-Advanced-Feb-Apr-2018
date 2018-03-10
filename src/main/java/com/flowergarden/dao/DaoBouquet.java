/**
 * 
 */
package com.flowergarden.dao;

import java.util.List;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.GeneralFlower2;

/**
 * @author SOIERR
 *
 */
public interface DaoBouquet {
	
	public int createBouquet(int templateId, List<GeneralFlower2> listFlowers);
	public void updateBouquet(int templateId, List<GeneralFlower2> listFlowers);
	public void deleteBouquet(int templateId);
	
	public Bouquet<? extends GeneralFlower> getBouquet(int id);
	public Bouquet<? extends GeneralFlower> getBouquet(String name);

}
