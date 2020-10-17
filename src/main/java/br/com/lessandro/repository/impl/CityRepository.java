package br.com.lessandro.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lessandro.model.City;
import br.com.lessandro.repository.ICityRepository;

@Repository
public class CityRepository extends SimpleJpaRepository<City, Long>
		implements ICityRepository {

	private EntityManager entityManager;

	public CityRepository(EntityManager entityManager) {
		super(City.class, entityManager);
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public City findByCodeAndName(Integer code, String name) {
		Query query = entityManager.createQuery("select c from City c where c.code = :code and c.name = :name",
				City.class);
		query.setParameter("code", code);
		query.setParameter("name", name);
		List<City> cities = query.getResultList();
		return !cities.isEmpty() && cities.size() == 1 ? cities.get(0) : null;
	}
}
