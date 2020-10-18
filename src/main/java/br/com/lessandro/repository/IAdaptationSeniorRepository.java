package br.com.lessandro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lessandro.model.AdaptationSenior;

@Repository
public interface IAdaptationSeniorRepository extends JpaRepository<AdaptationSenior, Long> {
	
	AdaptationSenior findByDescription(String description);

	Page<AdaptationSenior> findAll(Pageable pageable);

}
