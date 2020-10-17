package br.com.lessandro.service;

import java.io.InputStream;
import java.util.List;

import br.com.lessandro.dto.PageDto;
import br.com.lessandro.dto.UbsDto;
import br.com.lessandro.model.Ubs;
import br.com.lessandro.resources.exception.ValidationException;

public interface IUbsService {

	PageDto<UbsDto> getAllUbs(int page, int size) throws ValidationException;
	List<Ubs> readUbsFromCSV(InputStream inputStream);
	void saveUbsList(List<Ubs> ubsList);
	
}