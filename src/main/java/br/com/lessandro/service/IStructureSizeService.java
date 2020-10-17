package br.com.lessandro.service;

import br.com.lessandro.dto.PageDto;
import br.com.lessandro.dto.StructureSizeDto;
import br.com.lessandro.model.StructureSize;
import br.com.lessandro.resources.exception.ValidationException;

public interface IStructureSizeService {

	PageDto<StructureSizeDto> getAllStructures(int page, int size) throws ValidationException;

	StructureSize findByDescription(String description);

	StructureSize prepareStructureSize(StructureSize structureSize);

}