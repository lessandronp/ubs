package br.com.lessandro.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.lessandro.dto.PageDto;
import br.com.lessandro.dto.ScoreDto;
import br.com.lessandro.model.AdaptationSenior;
import br.com.lessandro.model.MedicalEquipment;
import br.com.lessandro.model.Medicine;
import br.com.lessandro.model.Score;
import br.com.lessandro.model.StructureSize;
import br.com.lessandro.repository.IScoreRepository;
import br.com.lessandro.resources.exception.ValidationException;
import br.com.lessandro.service.IAdaptationSeniorService;
import br.com.lessandro.service.IMedicalEquipmentService;
import br.com.lessandro.service.IMedicineService;
import br.com.lessandro.service.IScoreService;
import br.com.lessandro.service.IStructureSizeService;
import br.com.lessandro.validator.PageValidator;

@Service
public class ScoreService implements IScoreService {

	@Autowired
	private IScoreRepository scoreRepository;
	
	@Autowired
	private IAdaptationSeniorService adaptationSeniorService;

	@Autowired
	private IStructureSizeService structureSizeService;

	@Autowired
	private IMedicalEquipmentService medicalEquipmentService;

	@Autowired
	private IMedicineService medicineService;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PageDto<ScoreDto> getAllScores(int page, int size) throws ValidationException {
		PageValidator.validatePageSize(page, size);
		Page<Score> scores = new PageImpl<>(new ArrayList<>());
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "creationDate");
		scores = scoreRepository.findAll(pageable);
		List<ScoreDto> adapatationsDto = Arrays.asList(modelMapper.map(scores.getContent(), ScoreDto[].class));
		return new PageDto<>(adapatationsDto, scores.getNumber(), scores.getSize(), scores.getTotalElements(),
				scores.getTotalPages(), scores.isLast());
	}

	@Override
	public Score findByScore(StructureSize structureSize,
			AdaptationSenior adaptationSenior, MedicalEquipment medicalEquipment, Medicine medicine) {
		return scoreRepository.findByStructureSizeAndAdaptationSeniorAndMedicalEquipmentAndMedicine(structureSize,
				adaptationSenior, medicalEquipment, medicine);

	}
	
	@Override
	public Score prepareScore(Score score) {
		prepareScoreDetail(score);
		if (score.getStructureSize().getId() != null && score.getAdaptationSenior().getId() != null
				&& score.getMedicalEquipment().getId() != null && score.getMedicine().getId() != null) {
			Score scoreDB = this.findByScore(score.getStructureSize(), score.getAdaptationSenior(), 
					score.getMedicalEquipment(), score.getMedicine());
			if (scoreDB != null) {
				return scoreDB;
			}
		} 
		return score;
	}
	
	private void prepareScoreDetail(Score score) {
		score.setStructureSize(structureSizeService.prepareStructureSize(score.getStructureSize()));
		score.setAdaptationSenior(adaptationSeniorService.prepareAdaptationSenior(score.getAdaptationSenior()));
		score.setMedicalEquipment(medicalEquipmentService.prepareMedicalEquipment(score.getMedicalEquipment()));
		score.setMedicine(medicineService.prepareMedicine(score.getMedicine()));
	}

}
