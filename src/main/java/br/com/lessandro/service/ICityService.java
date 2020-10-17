package br.com.lessandro.service;

import br.com.lessandro.dto.CityDto;
import br.com.lessandro.dto.PageDto;
import br.com.lessandro.model.City;
import br.com.lessandro.resources.exception.ValidationException;

public interface ICityService {

	PageDto<CityDto> getAllCities(int page, int size) throws ValidationException;
	City findByCodeAndName(Integer code, String name);
	City prepareCity(City city);
}