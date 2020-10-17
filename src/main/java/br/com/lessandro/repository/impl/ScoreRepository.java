package br.com.lessandro.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lessandro.model.AdaptationSenior;
import br.com.lessandro.model.MedicalEquipment;
import br.com.lessandro.model.Medicine;
import br.com.lessandro.model.Score;
import br.com.lessandro.model.StructureSize;
import br.com.lessandro.repository.IScoreRepository;

@Repository
public class ScoreRepository extends SimpleJpaRepository<Score, Long> implements IScoreRepository {

	private EntityManager entityManager;

	public ScoreRepository(EntityManager entityManager) {
		super(Score.class, entityManager);
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Score findByStructureSizeAndAdaptationSeniorAndMedicalEquipmentAndMedicine(StructureSize structureSize,
			AdaptationSenior adaptationSenior, MedicalEquipment medicalEquipment, Medicine medicine) {
		StringBuilder sql = new StringBuilder();
		sql.append("select s from Score s ");
		sql.append("inner join s.structureSize ss ");
		sql.append("inner join s.adaptationSenior a ");
		sql.append("inner join s.medicalEquipment me ");
		sql.append("inner join s.medicine m ");
		sql.append("where ss.id = :idStructure ");
		sql.append("and a.id = :idAdaptation ");
		sql.append("and me.id = :idMedical ");
		sql.append("and m.id = :idMedicine ");
		Query query = entityManager.createQuery(sql.toString(), Score.class);
		query.setParameter("idStructure", structureSize.getId());
		query.setParameter("idAdaptation", adaptationSenior.getId());
		query.setParameter("idMedical", medicalEquipment.getId());
		query.setParameter("idMedicine", medicine.getId());
		List<Score> scores = query.getResultList();
		entityManager.flush();
		return !scores.isEmpty() && scores.size() == 1 ? scores.get(0) : null;
	}
}
