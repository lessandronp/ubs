package br.com.lessandro.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.lessandro.model.Ubs;
import br.com.lessandro.resources.exception.ValidationException;

@Repository
public class UbsRepositoryBatch {

	private EntityManagerFactory emf;

	@Autowired
    public UbsRepositoryBatch(EntityManagerFactory emf) {
        this.emf = emf;
    }
	
	public void saveUbs(Ubs ubs) throws ValidationException {
		try { 
			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.merge(ubs);
			entityManager.flush();
			entityManager.getTransaction().commit();
			entityManager.close();
		} catch (Exception e) {
			throw new ValidationException(e.getMessage());
		}
	}
}
