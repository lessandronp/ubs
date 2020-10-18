package br.com.lessandro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lessandro.model.AdaptationSenior;
import br.com.lessandro.model.MedicalEquipment;
import br.com.lessandro.model.Medicine;
import br.com.lessandro.model.Score;
import br.com.lessandro.model.StructureSize;

@Repository
public interface IScoreRepository extends JpaRepository<Score, Long> {

	Score findByStructureSizeAndAdaptationSeniorAndMedicalEquipmentAndMedicine(StructureSize structureSize,
			AdaptationSenior adaptationSenior, MedicalEquipment medicalEquipment, Medicine medicine);

	Page<Score> findAll(Pageable pageable);

}
