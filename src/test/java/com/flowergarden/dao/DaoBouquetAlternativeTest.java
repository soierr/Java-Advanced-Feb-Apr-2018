/**
 * 
 */
package com.flowergarden.dao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.Bouquet2;
import com.flowergarden.dao.impl.DaoBouquetImpl;
import com.flowergarden.dao.model.BouquetImpl;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.GeneralFlower2;
import com.flowergarden.rules.CreateDropDbObjectsRule;
import com.flowergarden.run.Run;

/**
 * @author SOIERR
 *
 */
@SuppressWarnings("unchecked")
public class DaoBouquetAlternativeTest {
	
	Bouquet2<GeneralFlower2> bouquet = null;
	
	String bouquetJson = null;
	
	String sampleBouquetName = "First Date";
	
	DaoDataSource dataSource = mock(DaoDataSource.class);
	
	DaoBouquetAlternative daoBouquetAlternative = Run.startContainer().getBean("daoBouquetAlternative", DaoBouquetAlternative.class);
	
	@Rule
	public CreateDropDbObjectsRule createDropDbObjectsRule = new CreateDropDbObjectsRule();
	
	@Before
	public void prepareBoquet(){
		
		DaoBouquet daoBouquet = null;
		
		/*if thenReturn then Connection is getting only once */
		when(dataSource.getConnection()).then(new Answer<Connection>() {
			
			@Override
			public Connection answer(InvocationOnMock invocation) throws Throwable {

				return createDropDbObjectsRule.getConnection();
			}
		});
		
		daoBouquet = new DaoBouquetImpl(dataSource, createDropDbObjectsRule.getSqlScripts());
		
		bouquet = (Bouquet2<GeneralFlower2>)daoBouquet.getBouquet(sampleBouquetName);
				
		daoBouquetAlternative.save(bouquet);
	}

	@Test
	public void readTest(){
		
		Bouquet2<GeneralFlower2> bouquet = daoBouquetAlternative.read();
		
		Bouquet<GeneralFlower> bouquetOld = (Bouquet<GeneralFlower>) daoBouquetAlternative.read();
		
		Assert.assertNotNull(bouquet);
		
		Assert.assertNotNull(bouquetOld);
		
		Assert.assertTrue(sampleBouquetName.equals(bouquet.getName()));
		
		Assert.assertEquals(bouquet.getPrice(), bouquetOld.getPrice(), 0);
		
	}
	
	@Test
	public void saveTest(){
		
		String changedBouquetName = "First Data. JSON";
		
		DaoBouquet daoBouquet = new DaoBouquetImpl(dataSource, createDropDbObjectsRule.getSqlScripts());
		
		bouquet = (Bouquet2<GeneralFlower2>)daoBouquet.getBouquet(sampleBouquetName);
		
		Assert.assertTrue(sampleBouquetName.equals(bouquet.getName()));
		
		((BouquetImpl)bouquet).setName(changedBouquetName);
		
		daoBouquetAlternative.save(bouquet);
		
		Bouquet2<GeneralFlower2> bouquetJson = daoBouquetAlternative.read();
		
		Assert.assertTrue(changedBouquetName.equals(bouquetJson.getName()));
		
	}
	
	@Test
	public void saveWithoutInjectionTest(){
		
		String changedBouquetName = "First Data. JSON";
		
		DaoBouquet daoBouquet = new DaoBouquetImpl(dataSource, createDropDbObjectsRule.getSqlScripts());
		
		bouquet = (Bouquet2<GeneralFlower2>)daoBouquet.getBouquet(sampleBouquetName);
		
		((BouquetImpl)bouquet).setName(changedBouquetName);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		Configuration config = new Configuration();
        MappedNamespaceConvention conv = new MappedNamespaceConvention(config);
        Writer writer = new OutputStreamWriter(baos);
        XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(conv, writer);
        
        Marshaller marshaller = null;
        JAXBContext jaxbContext = null;
         
        
        try{
            
        	jaxbContext = JAXBContext.newInstance(BouquetImpl.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(bouquet, xmlStreamWriter);
            
        }catch(JAXBException jaxbe){
            
            jaxbe.printStackTrace();
        }
        
        System.out.println(baos.toString());
        
        ((BouquetImpl)bouquet).setName("Some Bouquet");
        

        try{   
        	
        	baos.flush();
        	baos.reset();
            marshaller.marshal(bouquet, xmlStreamWriter);
            
        }catch(IOException | JAXBException jaxbe){
            
            jaxbe.printStackTrace();
        }
        
        System.out.println(baos.toString());
	}
	
}
