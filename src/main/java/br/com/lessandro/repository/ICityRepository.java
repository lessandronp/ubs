package br.com.lessandro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.lessandro.model.City;

public interface ICityRepository {
	
	City findByCodeAndName(Integer code, String name);

	Page<City> findAll(Pageable pageable);
	
}
