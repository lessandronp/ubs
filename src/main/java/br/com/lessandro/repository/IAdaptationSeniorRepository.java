package br.com.lessandro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.lessandro.model.AdaptationSenior;

public interface IAdaptationSeniorRepository {
	
	AdaptationSenior findByDescription(String description);

	Page<AdaptationSenior> findAll(Pageable pageable);

}
