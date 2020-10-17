package br.com.lessandro.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.lessandro.model.Ubs;
import br.com.lessandro.repository.IUbsRepository;
import br.com.lessandro.resources.exception.ValidationException;

@Repository
public class UbsRepository implements IUbsRepository {

	private EntityManagerFactory emf;

	@Autowired
    public UbsRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }
	
	@Override
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

	@Override
	public Page<Ubs> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
}
