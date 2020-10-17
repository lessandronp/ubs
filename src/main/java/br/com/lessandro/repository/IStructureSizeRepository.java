package br.com.lessandro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.lessandro.model.StructureSize;

public interface IStructureSizeRepository {
	
	StructureSize findByDescription(String description);

	Page<StructureSize> findAll(Pageable pageable);
	
}
