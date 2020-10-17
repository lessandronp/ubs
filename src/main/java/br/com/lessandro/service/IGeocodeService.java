package br.com.lessandro.service;

import br.com.lessandro.dto.GeocodeDto;
import br.com.lessandro.dto.PageDto;
import br.com.lessandro.model.Geocode;
import br.com.lessandro.resources.exception.ValidationException;

public interface IGeocodeService {

	PageDto<GeocodeDto> getAllGeocodes(int page, int size) throws ValidationException;

	Geocode findByLatitudeAndLongitude(String latitude, String longitude);

	Geocode prepareGeocode(Geocode geocode);
	
}