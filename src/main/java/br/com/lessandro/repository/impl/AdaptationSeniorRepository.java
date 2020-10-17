package br.com.lessandro.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lessandro.model.AdaptationSenior;
import br.com.lessandro.repository.IAdaptationSeniorRepository;

@Repository
public class AdaptationSeniorRepository extends SimpleJpaRepository<AdaptationSenior, Long>
		implements IAdaptationSeniorRepository {

	private EntityManager entityManager;

	public AdaptationSeniorRepository(EntityManager entityManager) {
		super(AdaptationSenior.class, entityManager);
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AdaptationSenior findByDescription(String description) {
		Query query = entityManager.createQuery("select a from AdaptationSenior a where a.description = :description",
				AdaptationSenior.class);
		query.setParameter("description", description);
		List<AdaptationSenior> adaptationsSenior = query.getResultList();
		return !adaptationsSenior.isEmpty() && adaptationsSenior.size() == 1 ? adaptationsSenior.get(0) : null;
	}
}
