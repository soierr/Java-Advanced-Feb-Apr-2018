/**
 * 
 */
package com.flowergarden.flowers;

/**
 * @author SOIERR
 *
 */
public class GeneralFlower2 extends GeneralFlower{
	
	private int id;
	
	private String name = null;
	
	private Long price = 0L;
	
	private int petals = 0;
	
	private boolean spike = false;
	
	private int bouquetId = 0;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public Long getPriceLong() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPriceLong(Long price) {
		this.price = price;
		super.price = price/100f;
	}

	/**
	 * @return the petals
	 */
	public int getPetals() {
		return petals;
	}

	/**
	 * @param petals the petals to set
	 */
	public void setPetals(int petals) {
		this.petals = petals;
	}

	/**
	 * @return the spike
	 */
	public boolean isSpike() {
		return spike;
	}

	/**
	 * @param spike the spike to set
	 */
	public void setSpike(boolean spike) {
		this.spike = spike;
	}
	
	
	public void setLength(int length){
		
		super.length = length;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public final float getPrice() {
		// TODO Auto-generated method stub
		return super.getPrice();
	}

	/**
	 * @return the bouquet_id
	 */
	public int getBouquetId() {
		return bouquetId;
	}

	/**
	 * @param bouquet_id the bouquet_id to set
	 */
	public void setBouquetId(int bouquetId) {
		this.bouquetId = bouquetId;
	}
	
}
