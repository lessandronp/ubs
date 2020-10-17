package br.com.lessandro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.lessandro.model.Medicine;

public interface IMedicineRepository {
	
	Medicine findByDescription(String description);

	Page<Medicine> findAll(Pageable pageable);
	
}
