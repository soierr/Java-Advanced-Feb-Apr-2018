/**
 * 
 */
package com.flowergarden.dao;

import java.util.List;

import com.flowergarden.flowers.GeneralFlower2;

/**
 * @author SOIERR
 *
 */
public interface DaoFlower {
	
	public List<GeneralFlower2> getFlowers();
	public int create(GeneralFlower2 flower);
	public void update(GeneralFlower2 oldFlower, GeneralFlower2 newFlower);
	public void delete(GeneralFlower2 flower);

}
