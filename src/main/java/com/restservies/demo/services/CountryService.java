package com.restservies.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.restservies.demo.beans.Country;
import com.restservies.demo.controllers.AddResponse;
import com.restservies.demo.repositories.CountryRepository;

@Component
@Service
public class CountryService {

	@Autowired
	CountryRepository countryrep;
	
	public List<Country> getAllCountries() {
		
		 List<Country> countrie=countryrep.findAll();
		 return countrie;
    }
	
	public Country getCountryById(int id) {
		List<Country> countrie=countryrep.findAll();
		Country country=null;
		
		for (Country c : countrie) {
			if (c.getId() == id)
				country = c;

		}
		return country; 
    }
	
	public Country getCountryByName(String countryName) {
		
		List<Country> countries=countryrep.findAll();  
		Country country=null;
		for (Country c : countries) {
			if (c.getCountryName().equalsIgnoreCase(countryName)) 
				country = c;
				
		}
		return country;
	}
	
	public Country addCountry(Country country) {
		
		
		//country.setId(getMaxId());
		countryrep.save(country);
		return country;
		
	}

	public int getMaxId() {
		
		
		return countryrep.findAll().size()+1;
		
	}
	
	public Country updateCountry(Country country) {
		return countryrep.save(country);
		 		
	}
	
	public AddResponse deleteCountry(int id) {
		
		countryrep.deleteById(id);
		AddResponse res = new AddResponse();
		res.setMsg("Country deleted successfully");
		res.setId(id);
		return res;
	}
	
	public void deleteCountry(Country country) {
		
		countryrep.delete(country);
		
	}
}
