/**
 * 
 */
package com.flowergarden.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamWriter;

import org.springframework.stereotype.Service;

import com.flowergarden.bouquet.Bouquet2;
import com.flowergarden.dao.DaoBouquet;
import com.flowergarden.dao.impl.DaoBouquetImpl;
import com.flowergarden.flowers.GeneralFlower2;

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
	
	public float getPrice(Bouquet2<GeneralFlower2> bouquet){
		
		return bouquet.getPrice();
	}
	
	public String getBouquet(int bouquetId){
		
		String json = null;
		
		Bouquet2<GeneralFlower2> bouquet = (Bouquet2<GeneralFlower2>) daoBouquet.getBouquet(bouquetId);
		
		if(bouquet == null){
			
			return null;
		}
		
		try{
			
			marshaller.marshal(bouquet, xmlStreamWriter);
			
		}catch(JAXBException je){
			
			je.printStackTrace();
		}
		
		try{
			byteOutputStream.flush();
		}catch(IOException ioe){
			
			ioe.printStackTrace();
		}
		
		 json = byteOutputStream.toString();
		 byteOutputStream.reset();
				
		return json;
	}

}
