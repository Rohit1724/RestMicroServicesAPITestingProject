package com.restservies.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.restservies.demo.beans.Country;
import com.restservies.demo.controllers.CountryController;
import com.restservies.demo.services.CountryService;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
//@SpringBootTest(classes= {ControllerMackitoTests.class})
public class ControllerMackitoTests {

	@Mock
	CountryService countryService;
	
	@InjectMocks
	CountryController countryController;
	
	List<Country> mycountries;
	Country country;
	
	@Test
	@Order(1)
	public void test_getAllCountries() {
		
		mycountries = List.of(new Country(1, "India", "IN"), new Country(2, "USA", "US"),
				new Country(3, "UK", "UK"));

		when(countryService.getAllCountries()).thenReturn(mycountries);
		ResponseEntity<List<Country>> res=countryController.getCountry();
		
		assertEquals(HttpStatus.FOUND,res.getStatusCode());
		assertEquals(3,res.getBody().size());
	}
	
	@Test
	@Order(2)
	public void test_getCountriesById() {
		
		country=new Country(2,"USA","Washington");
		int countryID=2;
		when(countryService.getCountryById(countryID)).thenReturn(country);
		ResponseEntity<Country> res=countryController.getCountryById(countryID);
		assertEquals(HttpStatus.FOUND,res.getStatusCode());
	
		assertEquals(countryID,res.getBody().getId());
	
	}
	
	@Test
	@Order(3)
	public void test_getCountriesByName() {
		
		country=new Country(2,"USA","Washington");
		String countryName="USA";
		
		when(countryService.getCountryByName(countryName)).thenReturn(country);
		ResponseEntity<Country> res=countryController.getCountryByName(countryName);
		assertEquals(HttpStatus.FOUND,res.getStatusCode());
	
		assertEquals(countryName,res.getBody().getCountryName());
	
	}
	
	@Test
	@Order(4)
	public void test_addCountries() {
		
		country=new Country(4,"Japan","Tokyo");
		
		when(countryService.addCountry(country)).thenReturn(country);
		
		when(countryService.addCountry(country)).thenReturn(country);
		ResponseEntity<Country> res=countryController.addCountry(country);
		assertEquals(HttpStatus.CREATED,res.getStatusCode());
		
		assertEquals(country,res.getBody());
		
	}
	
	@Test
	@Order(5)
	public void test_updateCountries() {
		
		country=new Country(4,"Naple","Kar");
		int countryID=4;
		when(countryService.getCountryById(countryID)).thenReturn(country);
		when(countryService.updateCountry(country)).thenReturn(country);
		ResponseEntity<Country> res=countryController.updateCountry(countryID,country);
		assertEquals(HttpStatus.OK,res.getStatusCode());
		
		assertEquals(country,res.getBody());
		assertEquals(countryID,res.getBody().getId());
		assertEquals("Naple",res.getBody().getCountryName());
		assertEquals("Kar",res.getBody().getCountryCapital());
	}
	
	@Test
	@Order(6)
	public void test_deleteCountries() {
		
		country=new Country(4,"Naple","Kar");
		int countryID=4;
		when(countryService.getCountryById(countryID)).thenReturn(country);
		

		ResponseEntity<Country> res=countryController.deleteCountry(countryID);
		assertEquals(HttpStatus.OK,res.getStatusCode());
				
	}
	
}
