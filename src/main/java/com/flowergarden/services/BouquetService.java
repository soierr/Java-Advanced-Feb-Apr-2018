/**
 * 
 */
package com.flowergarden.services;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamWriter;

import org.springframework.stereotype.Service;

import com.flowergarden.bouquet.Bouquet2;
import com.flowergarden.dao.DaoBouquet;
import com.flowergarden.dao.impl.DaoBouquetImpl;
import com.flowergarden.flowers.GeneralFlower2;
import com.flowergarden.properties.FreshnessInteger;
import com.flowergarden.services.ResponseBouquetFreshness.RESULT;

/**
 * @author SOIERR
 *
 */
@SuppressWarnings("unchecked")
@Service("bouquetService")
public class BouquetService {
	
	@Resource(type=DaoBouquetImpl.class)
	private DaoBouquet daoBouquet ;
	
	@Resource(type=Marshaller.class)
	private Marshaller marshaller = null;
	
	@Resource(type=XMLStreamWriter.class)
	private XMLStreamWriter xmlStreamWriter = null;
	
	@Resource(type=ByteArrayOutputStream.class)
	private ByteArrayOutputStream byteOutputStream = null;
	
	public float getPrice(int bouquetId){
		
		Bouquet2<GeneralFlower2> bouquet = (Bouquet2<GeneralFlower2>) daoBouquet.getBouquet(bouquetId);
		
		if(bouquet == null){
			
			return -1;
		}
		
		return bouquet.getPrice();
	}

	public ResponseBouquetFreshness decrementFreshness(int bouquetId){
		
		Bouquet2<GeneralFlower2> bouquet = (Bouquet2<GeneralFlower2>) daoBouquet.getBouquet(bouquetId);
		
		if(bouquet == null){
			
			return new ResponseBouquetFreshness(RESULT.NOT_FOUND);
		}
		
		List<GeneralFlower2> listFlowers = (List<GeneralFlower2>) bouquet.getFlowers();

		List<GeneralFlower2> listFlowersUpdated = new ArrayList<>();
		List<GeneralFlower2> listFlowersZeroPrice = new ArrayList<>();
		
		int fr = 0;
		
		Iterator<GeneralFlower2> it = listFlowers.iterator();
		GeneralFlower2 flower;

		while(it.hasNext()){
			
			flower = it.next();
			
			if((fr = flower.getFreshness().getFreshness()) > 0){				
				flower.setFreshness(new FreshnessInteger(fr-1));
				listFlowersUpdated.add(flower);
			}else{
				listFlowersZeroPrice.add(flower);
			}
		}
		
		ResponseBouquetFreshness response = new ResponseBouquetFreshness();
		
		ResponseBouquetFreshness.RESULT res = null;
		
		if(listFlowersUpdated.size() == listFlowers.size()){
			
			res = RESULT.OK_ALL;
			
		}else if(listFlowersZeroPrice.size() == listFlowers.size()){
			
			res = RESULT.NOK_ALL;
			
		}else if(!listFlowersZeroPrice.isEmpty()){
			
			res = RESULT.OK_SPECIFIC;
		}
			
		response.setResult(res);
		response.setListFlowersUpdated(listFlowersUpdated);
		response.setListFlowersZeroPrice(listFlowersZeroPrice);

		daoBouquet.updateAndAdd(bouquet);
		
		return response;
	}
	
	public Bouquet2<GeneralFlower2> getBouquet(int bouquetId){
		
		return (Bouquet2<GeneralFlower2>) daoBouquet.getBouquet(bouquetId);
	}
	
	public List<GeneralFlower2> getFlowers(int bouquetId){
		
		Bouquet2<GeneralFlower2> bouquet = (Bouquet2<GeneralFlower2>) daoBouquet.getBouquet(bouquetId);
		
		return bouquet == null ? null : (List<GeneralFlower2>)daoBouquet.getBouquet(bouquetId).getFlowers();
	}
}
