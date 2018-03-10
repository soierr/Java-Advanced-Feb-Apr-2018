/**
 * 
 */
package com.flowergarden.dao.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.flowergarden.bouquet.Bouquet2;
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
	
	/**
	 * 
	 */
	public BouquetImpl(int id, String name) {
		
		this.id = id;
		this.name = name;
	}
	
	@Override
	public long getPriceAssembling() {
		
		return this.priceAssembling;
	}

	 
	@Override
	public String getName() {

		return this.name;
	}
	
	/*We decide to return price as accurate as possible
	 *offering to client to decide how to round it*/
	@Override
	public long getPriceTotal() {
		
		return calculatePrice();
	}

	
	@Override
	public float getPrice() {
	
		/*Trying to fix rounding problem of inherited getPrice*/
		return calculatePrice()/100;
	}
	
	private long calculatePrice(){
		
		long price = priceAssembling;
		
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
	
	/* (non-Javadoc)
	 * @see com.flowergarden.bouquet.Bouquet#getFlowers()
	 */
	@Override
	public Collection<GeneralFlower2> getFlowers() {
		// TODO Auto-generated method stub
		return listFlowers;
	}
	
	/* (non-Javadoc)
	 * @see com.flowergarden.bouquet.Bouquet#sortByFreshness()
	 */
	@Override
	public void sortByFreshness() {
	
		Collections.sort(listFlowers);
	}
	
	/* (non-Javadoc)
	 * @see com.flowergarden.bouquet.Bouquet#searchFlowersByLenght(int, int)
	 */
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
	
}
