package br.com.lessandro.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.lessandro.model.Ubs;

@Repository
public interface IUbsRepository extends PagingAndSortingRepository<Ubs, Long> {

	@Query(value = "SELECT u.* FROM ubs u INNER JOIN geocode g ON u.geocode_id = g.geocode_id "
			+ "ORDER  BY (SELECT 6371 * Acos(Cos(Radians(:latitude)) * Cos( "
			+ "				Radians(g.latitude)) * Cos( "
			+ "				Radians(:longitude) - Radians(g.longitude)) "
			+ "				+ Sin( Radians(:latitude)) * Sin( Radians(g.latitude))) " + "AS distance "
			+ "		  ) 	", nativeQuery = true)
	Page<Ubs> findTopUbsByGeocode(@Param("latitude") BigDecimal latitude, @Param("longitude") BigDecimal longitude,
			Pageable pageable);

}
