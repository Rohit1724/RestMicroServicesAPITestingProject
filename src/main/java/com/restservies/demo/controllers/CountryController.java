package com.restservies.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restservies.demo.beans.Country;
import com.restservies.demo.services.CountryService;

@RestController
public class CountryController {

	@Autowired
	CountryService countryService;

	@GetMapping("/getcountries")
	public ResponseEntity<List<Country>> getCountry() {

		try{
		List<Country> countries=countryService.getAllCountries();
		return new ResponseEntity<List<Country>>(countries, HttpStatus.FOUND);

	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	}

	@GetMapping("/getcountries/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable(value = "id") int id) {

		try {

			Country country = countryService.getCountryById(id);
			return new ResponseEntity<Country>(country, HttpStatus.FOUND);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping("/getcountries/countryname")
	public ResponseEntity<Country> getCountryByName(@RequestParam(value = "name") String countryName) {

		try {

			Country country = countryService.getCountryByName(countryName);
			return new ResponseEntity<Country>(country, HttpStatus.FOUND);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

	}

	@PostMapping("/addcountry")
	public ResponseEntity<Country> addCountry(@RequestBody Country country) {

		try {

		    country = countryService.addCountry(country);
			return new ResponseEntity<Country>(country, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);

		}
		
		//return countryService.addCountry(country);
	}

	@PutMapping("/updatecountry/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable(value="id") int id,@RequestBody Country country) {

		try {
		Country exitsCountry=countryService.getCountryById(id);
		//exitsCountry.setId(id);
		exitsCountry.setCountryName(country.getCountryName());
		exitsCountry.setCountryCapital(country.getCountryCapital());
		
		Country updated_country=countryService.updateCountry(exitsCountry);
		return new ResponseEntity<Country>(updated_country, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);

		}
		
	}

	@DeleteMapping("/deletecountry/{id}")
	public ResponseEntity<Country> deleteCountry(@PathVariable(value = "id") int id) {
		Country country=null;
		try {
			country= countryService.getCountryById(id);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<Country>(country, HttpStatus.OK);

	}

}
