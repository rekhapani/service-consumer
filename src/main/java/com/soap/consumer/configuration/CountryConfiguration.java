package com.soap.consumer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.soap.consumer.client.CountryClient;

@Configuration
public class CountryConfiguration {
	
	@Bean
	  public Jaxb2Marshaller marshaller() {
	    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
	    // this package must match the package in the <generatePackage> specified in
	    // pom.xml
	    marshaller.setContextPath("com.consuming.webservice.wsdl");
	    return marshaller;
	  }

	  @Bean
	  public CountryClient countryClient(Jaxb2Marshaller marshaller) {
	    CountryClient client = new CountryClient();
	    client.setDefaultUri("http://localhost:8090/ws");
	    client.setMarshaller(marshaller);
	    client.setUnmarshaller(marshaller);
	    return client;
	  }


}
