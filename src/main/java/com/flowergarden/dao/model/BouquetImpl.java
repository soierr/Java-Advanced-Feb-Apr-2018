/**
 * 
 */
package com.flowergarden.dao.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.flowergarden.bouquet.Bouquet2;
import com.flowergarden.bouquet.Price;
import com.flowergarden.flowers.GeneralFlower2;

/**
 * @author SOIERR
 *
 */
public class BouquetImpl implements Bouquet2<GeneralFlower2>{
	
	private int id;
	
	private String name;
	
	private long priceAssembling = 0L;
	
	private List<GeneralFlower2> listFlowers = new ArrayList<GeneralFlower2>();
	
	private Price priceDetailed = new Price();
	
	/**
	 * 
	 */
	public BouquetImpl(String name) {

		this.name = name;
	}
	
	@Override
	public Price getPriceDetailed() {
		
		return priceDetailed;
	}
	
	public void setPriceDetailed(Price price){

		this.priceDetailed = price;
		this.priceAssembling = price.getPriceAssembling();
	}

	 
	@Override
	public String getName() {

		return this.name;
	}
		
	@Override
	public float getPrice() {
	
		/*Trying to fix rounding problem of inherited getPrice*/
		return (calculatePriceFlowers()+priceAssembling)/100f;
	}
	
	private long calculatePriceFlowers(){
		
		long price = 0L;
		
		for (GeneralFlower2 flower : listFlowers) {
			
			price += flower.getPriceLong();
		}
		
		return price;
		
	}

	@Override
	public void addFlower(GeneralFlower2 flower) {
		
		listFlowers.add(flower);
	}
	
	/*Only for mocking*/
	final void setFlowerListTest(List<GeneralFlower2> list){
		
		this.listFlowers = list;
	}

	@Override
	public Collection<GeneralFlower2> getFlowers() {
		// TODO Auto-generated method stub
		return listFlowers;
	}

	@Override
	public void sortByFreshness() {
	
		Collections.sort(listFlowers);
	}

	@Override
	public Collection<GeneralFlower2> searchFlowersByLenght(int start, int end) {
		
		List<GeneralFlower2> searchResult = new ArrayList<GeneralFlower2>();
		for (GeneralFlower2 flower : listFlowers) {			
			if (flower.getLength() >= start && flower.getLength() <= end) {
				searchResult.add(flower);
			}
		}

		return searchResult;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
