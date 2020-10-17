package br.com.lessandro.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lessandro.model.StructureSize;
import br.com.lessandro.repository.IStructureSizeRepository;

@Repository
public class StructureSizeRepository extends SimpleJpaRepository<StructureSize, Long>
		implements IStructureSizeRepository {

	private EntityManager entityManager;

	public StructureSizeRepository(EntityManager entityManager) {
		super(StructureSize.class, entityManager);
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public StructureSize findByDescription(String description) {
		Query query = entityManager.createQuery("select ss from StructureSize ss where ss.description = :description",
				StructureSize.class);
		query.setParameter("description", description);
		List<StructureSize> structures = query.getResultList();
		return !structures.isEmpty() && structures.size() == 1 ? structures.get(0) : null;
	}
}
