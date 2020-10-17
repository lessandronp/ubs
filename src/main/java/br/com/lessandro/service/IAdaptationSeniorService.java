package br.com.lessandro.service;

import br.com.lessandro.dto.AdaptationSeniorDto;
import br.com.lessandro.dto.PageDto;
import br.com.lessandro.model.AdaptationSenior;
import br.com.lessandro.resources.exception.ValidationException;

public interface IAdaptationSeniorService {

	PageDto<AdaptationSeniorDto> getAllAdaptations(int page, int size) throws ValidationException;

	AdaptationSenior findByDescription(String description);

	AdaptationSenior prepareAdaptationSenior(AdaptationSenior adaptationSenior);

}