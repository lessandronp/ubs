package br.com.lessandro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.lessandro.model.AdaptationSenior;
import br.com.lessandro.model.MedicalEquipment;
import br.com.lessandro.model.Medicine;
import br.com.lessandro.model.Score;
import br.com.lessandro.model.StructureSize;

public interface IScoreRepository {

	Score findByStructureSizeAndAdaptationSeniorAndMedicalEquipmentAndMedicine(StructureSize structureSize,
			AdaptationSenior adaptationSenior, MedicalEquipment medicalEquipment, Medicine medicine);

	Page<Score> findAll(Pageable pageable);

}
