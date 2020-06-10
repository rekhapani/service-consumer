package com.soap.consumer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.consuming.webservice.wsdl.GetCountryRequest;
import com.consuming.webservice.wsdl.GetCountryResponse;
import com.soap.consumer.client.CountryClient;
import com.soap.consumer.converter.CountryConverter;
import com.soap.consumer.service.CountryService;

@SpringBootApplication
@RestController
public class ServiceConsumerApplication {
	
	@Autowired
	CountryClient client;
	
	@Autowired
	CountryService countryService;
	
	

	public static void main(String[] args) {
		SpringApplication.run(ServiceConsumerApplication.class, args);
	}
	
	@PostMapping("/getCountry")
	public GetCountryResponse countryDetails (@RequestBody GetCountryRequest request) throws JAXBException, FileNotFoundException {
		
		
		
		//db call to fetch the count info to build req,res
		countryService.getCountryInfo(request.getName());
		
		GetCountryResponse response = new GetCountryResponse();
		
		response = client.getCountry(request.getName());
		
		//file directory creation
		
		//File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));
		File actualDirectory = CountryConverter.createDirectory("actual");
		//File expectedDirectory = new File(TEMP_DIRECTORY, "expected");
		System.out.println("actualDirectory.exists() :::: "+ actualDirectory.exists());
		
		//actualDirectory.mkdir();
		//expectedDirectory.mkdir();
		System.out.println("actualDirectory.exists() :::: "+ actualDirectory.exists());
		
		System.out.println("actualDirectory.getAbsolutePath() ::: "+ actualDirectory.getAbsolutePath());
		
		
		 JAXBContext context = JAXBContext.newInstance(GetCountryResponse.class);
	       // JAXBElement<ExpenseT> element = factory.createExpenseReport(expense);
	        Marshaller marshaller = context.createMarshaller();
	        marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
	        marshaller.marshal(response,System.out);
	        marshaller.marshal(response, new FileOutputStream(actualDirectory + File.separator + "countryReponse.xml")); 
		
		return client.getCountry(request.getName());
	}

}
