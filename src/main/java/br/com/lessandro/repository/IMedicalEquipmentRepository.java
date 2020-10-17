package br.com.lessandro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.lessandro.model.MedicalEquipment;

public interface IMedicalEquipmentRepository {
	
	MedicalEquipment findByDescription(String description);

	Page<MedicalEquipment> findAll(Pageable pageable);
	
}
