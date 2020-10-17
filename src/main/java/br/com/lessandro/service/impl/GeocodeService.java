package br.com.lessandro.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.lessandro.dto.GeocodeDto;
import br.com.lessandro.dto.PageDto;
import br.com.lessandro.model.Geocode;
import br.com.lessandro.repository.IGeocodeRepository;
import br.com.lessandro.resources.exception.ValidationException;
import br.com.lessandro.service.IGeocodeService;
import br.com.lessandro.validator.PageValidator;

@Service
public class GeocodeService implements IGeocodeService {

	@Autowired
	private IGeocodeRepository geocodeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PageDto<GeocodeDto> getAllGeocodes(int page, int size) throws ValidationException {
		PageValidator.validatePageSize(page, size);
		Page<Geocode> geocodes = new PageImpl<>(new ArrayList<>());
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "creationDate");
		geocodes = geocodeRepository.findAll(pageable);
		List<GeocodeDto> geocodesDto = Arrays
				.asList(modelMapper.map(geocodes.getContent(), GeocodeDto[].class));
		return new PageDto<>(geocodesDto, geocodes.getNumber(), geocodes.getSize(),
				geocodes.getTotalElements(), geocodes.getTotalPages(), geocodes.isLast());
	}
	
	@Override
	public Geocode findByLatitudeAndLongitude(String latitude, String longitude) {
		return geocodeRepository.findByLatitudeAndLongitude(latitude, longitude);
	}
	
	@Override
	public Geocode prepareGeocode(Geocode geocode) {
		if (StringUtils.isNotEmpty(geocode.getLatitude()) && StringUtils.isNotEmpty(geocode.getLongitude())) {
			Geocode geocodeDB = this.findByLatitudeAndLongitude(geocode.getLatitude(), geocode.getLongitude());
			if (geocodeDB != null) {
				return geocodeDB;
			} 
		}
		return geocode;
	}

}
