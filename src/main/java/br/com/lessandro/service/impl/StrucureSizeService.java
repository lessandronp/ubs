package br.com.lessandro.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.lessandro.dto.PageDto;
import br.com.lessandro.dto.StructureSizeDto;
import br.com.lessandro.model.StructureSize;
import br.com.lessandro.repository.IStructureSizeRepository;
import br.com.lessandro.resources.exception.ValidationException;
import br.com.lessandro.service.IStructureSizeService;
import br.com.lessandro.validator.PageValidator;

@Service
public class StrucureSizeService implements IStructureSizeService {

	@Autowired
	private IStructureSizeRepository structureSizeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PageDto<StructureSizeDto> getAllStructures(int page, int size) throws ValidationException {
		PageValidator.validatePageSize(page, size);
		Page<StructureSize> structures = new PageImpl<>(new ArrayList<>());
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "creationDate");
		structures = structureSizeRepository.findAll(pageable);
		List<StructureSizeDto> adapatationsDto = Arrays
				.asList(modelMapper.map(structures.getContent(), StructureSizeDto[].class));
		return new PageDto<>(adapatationsDto, structures.getNumber(), structures.getSize(),
				structures.getTotalElements(), structures.getTotalPages(), structures.isLast());
	}
	
	@Override
	public StructureSize findByDescription(String description) {
		return structureSizeRepository.findByDescription(description);
	}
	
	@Override
	public StructureSize prepareStructureSize(StructureSize structureSize) {
		if (StringUtils.isNotEmpty(structureSize.getDescription())) {
			StructureSize structureSizeDB = this.findByDescription(structureSize.getDescription());
			if (structureSizeDB != null) {
				return structureSizeDB;
			}
		} 
		return structureSize;
	}

}
