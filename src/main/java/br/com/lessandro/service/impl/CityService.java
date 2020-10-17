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

import br.com.lessandro.dto.CityDto;
import br.com.lessandro.dto.PageDto;
import br.com.lessandro.model.City;
import br.com.lessandro.repository.ICityRepository;
import br.com.lessandro.resources.exception.ValidationException;
import br.com.lessandro.service.ICityService;
import br.com.lessandro.validator.PageValidator;

@Service
public class CityService implements ICityService {

	@Autowired
	private ICityRepository cityRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PageDto<CityDto> getAllCities(int page, int size) throws ValidationException {
		PageValidator.validatePageSize(page, size);
		Page<City> cities = new PageImpl<>(new ArrayList<>());
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "creationDate");
		cities = cityRepository.findAll(pageable);
		List<CityDto> adapatationsDto = Arrays.asList(modelMapper.map(cities.getContent(), CityDto[].class));
		return new PageDto<>(adapatationsDto, cities.getNumber(), cities.getSize(), cities.getTotalElements(),
				cities.getTotalPages(), cities.isLast());
	}

	@Override
	public City findByCodeAndName(Integer code, String name) {
		return cityRepository.findByCodeAndName(code, name);
	}

	@Override
	public City prepareCity(City city) {
		City cityDB = this.findByCodeAndName(city.getCode(), city.getName());
		if (cityDB != null) {
			return cityDB;
		}
		return city;
	}

}
