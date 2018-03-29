/**
 * 
 */
package com.flowergarden.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.jettison.mapped.MappedXMLStreamWriter;
import org.springframework.stereotype.Repository;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.dao.DaoBouquet;
import com.flowergarden.dao.DaoBouquetAlternative;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.run.Run;

/**
 * @author SOIERR
 *
 */
@SuppressWarnings("unchecked")
@Repository("daoBouquetAlternative")
public class DaoBouquetAlternativeImpl implements DaoBouquetAlternative{
	
	@Resource(name="bouquetJson")
	private File file;
	
//TODO Annotated Fields below needs to be reconsidered in regards of working in multithreaded environment
	
	@Resource(type=DaoBouquetImpl.class)
	private DaoBouquet daoBouquet ;
	
	@Resource(type=MappedXMLStreamWriter.class)
	private XMLStreamWriter xmlStreamWriter = null;
	
	@Resource(type=Marshaller.class)
	private Marshaller marshaller = null;
	
	@Resource(type=Unmarshaller.class)
	private Unmarshaller unmarshaller = null;
	
	public DaoBouquetAlternativeImpl() {
	}

	public DaoBouquetAlternativeImpl(String filename) {

		this.file = new File(filename);
	}
	
	public <T extends Bouquet<? extends GeneralFlower>> void save(T bouquet){
		
		ByteArrayOutputStream baos = Run.ctx.getBean("byteStream", ByteArrayOutputStream.class);
		
		try{
			
			marshaller.marshal(bouquet, xmlStreamWriter);
			writeJsonString(baos.toString());
			
		}catch(JAXBException jaxbe){
			
			jaxbe.printStackTrace();
		}finally{
			
			
			try{
				
				baos.flush();
				
			}catch(IOException ioe){
				
				ioe.printStackTrace();
				
			}finally{
				
				baos.reset();
			}
			
		}
		
	}

	public <T extends Bouquet<? extends GeneralFlower>> T read(){
		
		T bouquetT = null;
		
		try{
			
			XMLStreamReader xmlStreamReader = (XMLStreamReader) Run.ctx.getBean("xmlStreamReader", readJsonString());
			
			bouquetT = (T) unmarshaller.unmarshal(xmlStreamReader);
			
		}catch(JAXBException jaxbe){
			
			jaxbe.printStackTrace();
		}

		return bouquetT;
	}
	
	private String readJsonString(){
		
		String json = null;
		
		BufferedReader br = null;
		
		try{
			
			br = new BufferedReader(new FileReader(file));
			
			json = br.readLine();
			
		}catch(IOException ioe){
			
			ioe.printStackTrace();
			
		}finally{
			
			try{
				br.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
		
		return json;
	}
	
	private void writeJsonString(String json){
		
		BufferedWriter bw = null;
		
		try{
			
			bw = new BufferedWriter(new FileWriter(file));
			
			bw.write(json);

			
		}catch(IOException ioe){
			
			ioe.printStackTrace();
			
		}finally{
			
			try{
				bw.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
	}

}
