package com.soap.consumer.converter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.consuming.webservice.wsdl.Country;
import com.consuming.webservice.wsdl.Currency;
import com.consuming.webservice.wsdl.GetCountryRequest;
import com.consuming.webservice.wsdl.GetCountryResponse;
import com.soap.consumer.model.CountryEntity;

public class CountryConverter {
	
	@Autowired
	Jaxb2Marshaller marshaller;

	public static void convertCountryEntityTOPojo(CountryEntity countryEntity) throws FileNotFoundException, JAXBException {

		mapEntityTOPojo (countryEntity);
		//createReqResXML(countryEntity);
	}

	
	public static void mapEntityTOPojo (CountryEntity countryEntity ) throws FileNotFoundException, JAXBException {
		
		createRequest(countryEntity);
		createResponse(countryEntity);
		
	}
	
	public  static void createRequest (CountryEntity countryEntity) throws FileNotFoundException, JAXBException {		
		
		GetCountryRequest countryRequest = new GetCountryRequest ();	
		countryRequest.setName(countryEntity.getName());
		//create actual request xml
		generateReqXML(countryRequest);
	}
	
	public static void createResponse (CountryEntity countryEntity) throws FileNotFoundException, JAXBException {
		
		Country country = new Country ();
		//Currency currency = new Currency ();
		GetCountryResponse countryResponse = new GetCountryResponse();
		
		country.setName(countryEntity.getName());
		country.setCapital(countryEntity.getCapital());
		country.setPopulation(countryEntity.getPopulation());		
		country.setCurrency(Currency.fromValue(countryEntity.getCurrency()));		
		countryResponse.setCountry(country);
		//create expected response xml
		generateResXML(countryResponse);
		
	}
	
	/*
	 * public static void createReqResXML (CountryEntity countryEntity ) {
	 * 
	 * generateReqXML(countryEntity); generateResXML (countryEntity); }
	 */
	
	public static void generateReqXML (GetCountryRequest countryRequest) throws JAXBException, FileNotFoundException {
		
		/*
		 * StringWriter sw = new StringWriter(); Result result = new StreamResult(sw);
		 * 
		 * marshaller.marshal(countryRequest, result); result.
		 */
		
		 JAXBContext context = JAXBContext.newInstance("com.consuming.webservice.wsdl");
	       // JAXBElement<ExpenseT> element = factory.createExpenseReport(expense);
	        Marshaller marshaller = context.createMarshaller();
	        marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
	        marshaller.marshal(countryRequest,System.out);
	        marshaller.marshal(countryRequest, new FileOutputStream("countryRequest.xml")); 
	}
	
	public static void generateResXML (GetCountryResponse countryResponse) throws FileNotFoundException, JAXBException {
		
		 JAXBContext context = JAXBContext.newInstance("com.consuming.webservice.wsdl");
	       // JAXBElement<ExpenseT> element = factory.createExpenseReport(expense);
	        Marshaller marshaller = context.createMarshaller();
	        marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
	        marshaller.marshal(countryResponse,System.out);
	        marshaller.marshal(countryResponse, new FileOutputStream(createDirectory ("expected") + File.separator + "expectedResponse.xml")); 
		
	}
	
	public static File createDirectory (String folderName) {
			//file directory creation		
			File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));
			//File actualDirectory = new File(TEMP_DIRECTORY, "actual");
			File expectedDirectory = new File(TEMP_DIRECTORY, folderName);
			System.out.println("expectedDirectory.exists() :::: "+ expectedDirectory.exists());
			
			expectedDirectory.mkdir();
			expectedDirectory.mkdir();
			System.out.println("expectedDirectory.exists() :::: "+ expectedDirectory.exists());
			
			System.out.println("expectedDirectory.getAbsolutePath() ::: "+ expectedDirectory.getAbsolutePath());
			return expectedDirectory;
}
	
}