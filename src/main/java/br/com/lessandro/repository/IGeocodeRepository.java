package br.com.lessandro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lessandro.model.Geocode;

@Repository
public interface IGeocodeRepository extends JpaRepository<Geocode, Long> {
	
	Geocode findByLatitudeAndLongitude(String latitude, String longitude);
	
}
