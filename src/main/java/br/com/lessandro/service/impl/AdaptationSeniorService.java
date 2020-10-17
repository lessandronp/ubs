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

import br.com.lessandro.dto.AdaptationSeniorDto;
import br.com.lessandro.dto.PageDto;
import br.com.lessandro.model.AdaptationSenior;
import br.com.lessandro.repository.IAdaptationSeniorRepository;
import br.com.lessandro.resources.exception.ValidationException;
import br.com.lessandro.service.IAdaptationSeniorService;
import br.com.lessandro.validator.PageValidator;

@Service
public class AdaptationSeniorService implements IAdaptationSeniorService {

	@Autowired
	private IAdaptationSeniorRepository adaptationSeniorRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PageDto<AdaptationSeniorDto> getAllAdaptations(int page, int size) throws ValidationException {
		PageValidator.validatePageSize(page, size);
		Page<AdaptationSenior> adaptations = new PageImpl<>(new ArrayList<>());
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "creationDate");
		adaptations = adaptationSeniorRepository.findAll(pageable);
		List<AdaptationSeniorDto> adapatationsDto = Arrays
				.asList(modelMapper.map(adaptations.getContent(), AdaptationSeniorDto[].class));
		return new PageDto<>(adapatationsDto, adaptations.getNumber(), adaptations.getSize(),
				adaptations.getTotalElements(), adaptations.getTotalPages(), adaptations.isLast());
	}

	@Override
	public AdaptationSenior findByDescription(String description) {
		return adaptationSeniorRepository.findByDescription(description);
	}

	@Override
	public AdaptationSenior prepareAdaptationSenior(AdaptationSenior adaptationSenior) {
		if (StringUtils.isNotEmpty(adaptationSenior.getDescription())) {
			AdaptationSenior adaptationSeniorDB = this.findByDescription(adaptationSenior.getDescription());
			if (adaptationSeniorDB != null) {
				return adaptationSeniorDB;
			}
		}
		return adaptationSenior;
	}

}
