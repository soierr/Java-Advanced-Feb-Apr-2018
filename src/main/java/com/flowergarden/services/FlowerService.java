/**
 * 
 */
package com.flowergarden.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flowergarden.dao.DaoFlower;
import com.flowergarden.dao.impl.DaoFlowerImpl;
import com.flowergarden.flowers.GeneralFlower2;

/**
 * @author SOIERR
 *
 */
@Service("flowerService")
public class FlowerService {
	
	@Resource(type=DaoFlowerImpl.class)
	private DaoFlower daoFlower;
	
	public GeneralFlower2 getFlower(int flowerId){
		
		return daoFlower.getFlower(flowerId);
	}

}
