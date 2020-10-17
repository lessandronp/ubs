package br.com.lessandro.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lessandro.model.MedicalEquipment;
import br.com.lessandro.repository.IMedicalEquipmentRepository;

@Repository
public class MedicalEquipmentRepository extends SimpleJpaRepository<MedicalEquipment, Long>
		implements IMedicalEquipmentRepository {

	private EntityManager entityManager;

	public MedicalEquipmentRepository(EntityManager entityManager) {
		super(MedicalEquipment.class, entityManager);
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MedicalEquipment findByDescription(String description) {
		Query query = entityManager.createQuery("select m from MedicalEquipment m where m.description = :description",
				MedicalEquipment.class);
		query.setParameter("description", description);
		List<MedicalEquipment> medicamentals = query.getResultList();
		entityManager.flush();
		return !medicamentals.isEmpty() && medicamentals.size() == 1 ? medicamentals.get(0) : null;
	}
}
