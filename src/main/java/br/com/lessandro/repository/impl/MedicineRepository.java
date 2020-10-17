package br.com.lessandro.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lessandro.model.Medicine;
import br.com.lessandro.repository.IMedicineRepository;

@Repository
public class MedicineRepository extends SimpleJpaRepository<Medicine, Long>
		implements IMedicineRepository {

	private EntityManager entityManager;

	public MedicineRepository(EntityManager entityManager) {
		super(Medicine.class, entityManager);
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Medicine findByDescription(String description) {
		Query query = entityManager.createQuery("select m from Medicine m where m.description = :description",
				Medicine.class);
		query.setParameter("description", description);
		List<Medicine> medicines = query.getResultList();
		entityManager.flush();
		return !medicines.isEmpty() && medicines.size() == 1 ? medicines.get(0) : null;
	}
}
