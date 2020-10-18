package br.com.lessandro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lessandro.model.MedicalEquipment;

@Repository
public interface IMedicalEquipmentRepository extends JpaRepository<MedicalEquipment, Long> {
	
	MedicalEquipment findByDescription(String description);

	Page<MedicalEquipment> findAll(Pageable pageable);
	
}
