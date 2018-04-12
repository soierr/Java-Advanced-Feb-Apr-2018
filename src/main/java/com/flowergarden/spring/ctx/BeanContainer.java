/**
 * 
 */
package com.flowergarden.spring.ctx;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.commons.dbcp2.BasicDataSource;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamReader;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.flowergarden.dao.model.BouquetImpl;

/**
 * @author SOIERR
 *
 */
@Configuration
@ComponentScan("com.flowergarden")
public class BeanContainer {
	
	@Bean
	public Properties jdbcProperties(){
		
		return new PropertyLoader("jdbc.properties").getProperties();
	}
	
	@Bean
	public Properties sqlScripts(){
		
		return new PropertyLoader("sql-scripts.txt").getProperties();
	}
	
	@Bean
	public File bouquetJson(){
		
		return new File("resources/bouquet.json");
	}
	
	@Bean
	public BasicDataSource dBPool(){
		
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUrl(jdbcProperties().getProperty("jdbc.url"));
		
		return basicDataSource;
	}
	
	@Bean
	public ByteArrayOutputStream byteStream(){
		
		return new ByteArrayOutputStream();
	}
	
	@Bean 
	public org.codehaus.jettison.mapped.Configuration jettisonConfig(){
		
		return new org.codehaus.jettison.mapped.Configuration();
	}
	
	@Bean 
	public MappedNamespaceConvention mappedNameSpaceConvention(){
		
		return new MappedNamespaceConvention(jettisonConfig());
	}
	
	@Bean
	public Writer outputStreamWriter(){
		
		return new OutputStreamWriter(byteStream());
	}
	
	@Bean
	public XMLStreamWriter xmlStreamWriter(){
		
		return new MappedXMLStreamWriter(mappedNameSpaceConvention(), outputStreamWriter());
	}
	
	@Bean
	@Scope("prototype")
	public XMLStreamReader xmlStreamReader(String str) throws XMLStreamException, JSONException{
		
		return new MappedXMLStreamReader(new JSONObject(str), mappedNameSpaceConvention());
	}
	
	@Bean
	public JAXBContext jaxbContext() throws JAXBException{
		
		return JAXBContext.newInstance(BouquetImpl.class);
	}
	
	@Bean
	public Marshaller marshaller() throws JAXBException{
		
		return jaxbContext().createMarshaller();
	}
	
	@Bean
	public Unmarshaller unmarshaller() throws JAXBException{
		
		return jaxbContext().createUnmarshaller();
	}

}
