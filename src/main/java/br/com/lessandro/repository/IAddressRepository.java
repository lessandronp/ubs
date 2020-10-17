package br.com.lessandro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.lessandro.model.Address;

public interface IAddressRepository {
	
	Address findByStreetAndNeighborhood(String street, String neighborhood);

	Page<Address> findAll(Pageable pageable);
	
}
