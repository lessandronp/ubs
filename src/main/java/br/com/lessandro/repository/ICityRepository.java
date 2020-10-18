package br.com.lessandro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lessandro.model.City;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {
	
	City findByCodeAndName(Integer code, String name);

	Page<City> findAll(Pageable pageable);
	
}
