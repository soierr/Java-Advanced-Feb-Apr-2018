/**
 * 
 */
package com.flowergarden.output;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.flowergarden.bouquet.Bouquet2;
import com.flowergarden.bouquet.Price;
import com.flowergarden.flowers.GeneralFlower2;

/**
 * @author SOIERR
 *
 */
@SuppressWarnings("unchecked")
public class OutputSpaceIntTest {
	

	OutputSpaceInt output = new OutputSpaceScreenImpl();
	
	List<Bouquet2<GeneralFlower2>> bouquets = mock(ArrayList.class);
	
	Comparator<Bouquet2<GeneralFlower2>> comparator = mock(Comparator.class);
	
	Bouquet2<GeneralFlower2> bouquet = mock(Bouquet2.class);
	
	@Test
	public void printHelloTest(){
		
		/*simple check no any runtime exceptions*/
		output.printHello();
	}
	
	@Test
	public void printBouquetsSortTest(){
		
		when(bouquet.getId()).thenReturn(1);
		when(bouquet.getName()).thenReturn("MockBouquet");
		when(bouquet.getPriceDetailed()).thenAnswer(new Answer<Price>() {
			
			@Override
			public Price answer(InvocationOnMock invocation) throws Throwable {
				
				return new Price(5, 10);
			}
		});
		
		when(bouquets.iterator()).thenCallRealMethod();
		
		bouquets.add(bouquet);
		
		output.printBouquets(bouquets);
		
		verify(bouquets).sort(any(Comparator.class));
	}

}
