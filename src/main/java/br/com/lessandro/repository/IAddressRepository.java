package br.com.lessandro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lessandro.model.Address;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {
	
	Address findByStreetAndNeighborhood(String street, String neighborhood);

	Page<Address> findAll(Pageable pageable);
	
}
