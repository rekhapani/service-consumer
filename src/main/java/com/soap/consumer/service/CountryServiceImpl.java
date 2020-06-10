package com.soap.consumer.service;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soap.consumer.converter.CountryConverter;
import com.soap.consumer.model.CountryEntity;
import com.soap.consumer.repository.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	CountryRepository  countryRepository;
	
	/*
	 * @Autowired CountryConverter countryConverter;
	 */

	@Override
	public void getCountryInfo(String countryName) throws FileNotFoundException, JAXBException {
		
		CountryEntity countryEntity = null;
		countryEntity = countryRepository.findByName(countryName);
		
		CountryConverter.convertCountryEntityTOPojo (countryEntity);
		 
		 
	}

}
