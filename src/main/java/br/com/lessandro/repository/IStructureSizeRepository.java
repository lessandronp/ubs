package br.com.lessandro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lessandro.model.StructureSize;

@Repository
public interface IStructureSizeRepository extends JpaRepository<StructureSize, Long> {
	
	StructureSize findByDescription(String description);

	Page<StructureSize> findAll(Pageable pageable);
	
}
