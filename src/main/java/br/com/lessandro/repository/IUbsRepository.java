package br.com.lessandro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.lessandro.model.Ubs;
import br.com.lessandro.resources.exception.ValidationException;

public interface IUbsRepository {

	void saveUbs(Ubs ubs) throws ValidationException;

	Page<Ubs> findAll(Pageable pageable);
	
}
