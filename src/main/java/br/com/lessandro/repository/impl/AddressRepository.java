package br.com.lessandro.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lessandro.model.Address;
import br.com.lessandro.repository.IAddressRepository;

@Repository
public class AddressRepository extends SimpleJpaRepository<Address, Long> implements IAddressRepository {

	private EntityManager entityManager;

	public AddressRepository(EntityManager entityManager) {
		super(Address.class, entityManager);
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Address findByStreetAndNeighborhood(String street, String neighborhood) {
		Query query = entityManager.createQuery(
				"select a from Address a where a.street = :street and a.neighborhood = :neighborhood", Address.class);
		query.setParameter("street", street);
		query.setParameter("neighborhood", neighborhood);
		List<Address> addresses = query.getResultList();
		entityManager.flush();
		return !addresses.isEmpty() && addresses.size() == 1 ? addresses.get(0) : null;
	}
}
