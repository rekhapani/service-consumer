package com.soap.consumer.service;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import com.soap.consumer.model.CountryEntity;

public interface CountryService {
	
	public void getCountryInfo (String countryName) throws FileNotFoundException, JAXBException;

}
