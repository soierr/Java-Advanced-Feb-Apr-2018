/**
 * 
 */
package com.flowergarden.bouquet;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.flowers.Tulip;

/**
 * @author SOIERR
 *
 */
public class AddFlowerMockTest {
	
	Rose roseMocked = mock(Rose.class);
	Tulip tulipMocked = mock(Tulip.class);
	Chamomile chamomileMocked = mock(Chamomile.class);

	List<GeneralFlower> genFlowerList = mock(ArrayList.class);

	Bouquet<GeneralFlower> bouquet = new MarriedBouquet();
	
	@Before
	public void prepareBouquet(){
		
		((MarriedBouquet)bouquet).setFlowerListTest(genFlowerList);
	}
	
	@Test
	public void addFlowerTest(){
		
		bouquet.addFlower(roseMocked);
		bouquet.addFlower(chamomileMocked);
		
		verify(genFlowerList).add(roseMocked);
		verify(genFlowerList, times(2)).add(any(GeneralFlower.class));
	}
	
}
