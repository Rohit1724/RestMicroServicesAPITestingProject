package com.restservies.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restservies.demo.beans.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

	
	
}