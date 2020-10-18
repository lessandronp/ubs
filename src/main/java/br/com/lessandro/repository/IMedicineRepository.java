package br.com.lessandro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lessandro.model.Medicine;

@Repository
public interface IMedicineRepository extends JpaRepository<Medicine, Long> {
	
	Medicine findByDescription(String description);

	Page<Medicine> findAll(Pageable pageable);
	
}
