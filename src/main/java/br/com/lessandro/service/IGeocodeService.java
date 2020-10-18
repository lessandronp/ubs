package br.com.lessandro.service;

import java.math.BigDecimal;

import br.com.lessandro.dto.GeocodeDto;
import br.com.lessandro.dto.PageDto;
import br.com.lessandro.model.Geocode;
import br.com.lessandro.resources.exception.ValidationException;

public interface IGeocodeService {

	PageDto<GeocodeDto> getAllGeocodes(int page, int size) throws ValidationException;

	Geocode findByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);

	Geocode prepareGeocode(Geocode geocode);
	
}