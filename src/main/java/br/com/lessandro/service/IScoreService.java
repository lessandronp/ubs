package br.com.lessandro.service;

import br.com.lessandro.dto.PageDto;
import br.com.lessandro.dto.ScoreDto;
import br.com.lessandro.model.AdaptationSenior;
import br.com.lessandro.model.MedicalEquipment;
import br.com.lessandro.model.Medicine;
import br.com.lessandro.model.Score;
import br.com.lessandro.model.StructureSize;
import br.com.lessandro.resources.exception.ValidationException;

public interface IScoreService {

	PageDto<ScoreDto> getAllScores(int page, int size) throws ValidationException;

	Score findByScore(StructureSize structureSize, AdaptationSenior adaptationSenior, MedicalEquipment medicalEquipment,
			Medicine medicine);

	Score prepareScore(Score score);

}