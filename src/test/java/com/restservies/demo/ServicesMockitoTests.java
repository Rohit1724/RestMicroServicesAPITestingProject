package com.restservies.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restservies.demo.beans.Country;
import com.restservies.demo.repositories.CountryRepository;
import com.restservies.demo.services.CountryService;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class ServicesMockitoTests {

	@Mock
	private CountryRepository countryRepository;

	@InjectMocks
	private CountryService countryService;

	public List<Country> mycountries;

	@Test
	@Order(1)
	public void test_getAllCountries() {
		List<Country> mycountries = List.of(new Country(1, "India", "IN"), new Country(2, "USA", "US"),
				new Country(3, "UK", "UK"));

		when(countryRepository.findAll()).thenReturn(mycountries);

		assertEquals(3, countryService.getAllCountries().size());
	}

	@Test
	@Order(2)
	public void test_getCountryById() {
		List<Country> mycountries = List.of(new Country(1, "India", "IN"), new Country(2, "USA", "US"),
				new Country(3, "UK", "UK"));
		int countryId = 1;
		when(countryRepository.findAll()).thenReturn(mycountries);

		assertEquals(countryId, countryService.getCountryById(countryId).getId());
	}

	@Test
	@Order(3)
	public void test_getCountryByName() {
		List<Country> mycountries = List.of(new Country(1, "India", "IN"), new Country(2, "USA", "US"),
				new Country(3, "UK", "UK"));
		String countryName = "India";
		when(countryRepository.findAll()).thenReturn(mycountries);

		assertEquals(countryName, countryService.getCountryByName(countryName).getCountryName());
	}

	@Test
	@Order(4)
	public void test_addCountry() {
		Country country = new Country(4, "Germany", "DE");
		when(countryRepository.save(country)).thenReturn(country);

		assertEquals(country, countryService.addCountry(country));
	}
	
	@Test
	@Order(5)
	public void test_updateCountry() {
		Country country = new Country(1, "India", "Delhi");
		when(countryRepository.save(country)).thenReturn(country);

		assertEquals(country, countryService.updateCountry(country));
	}
	
	@Test
	@Order(5)
	public void test_deleteCountry() {
		Country country = new Country(1, "India", "Delhi");
		countryService.deleteCountry(country);

		verify(countryRepository,times(1)).delete(country);
	}
}
