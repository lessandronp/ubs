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

import br.com.lessandro.dto.MedicalEquipmentDto;
import br.com.lessandro.dto.PageDto;
import br.com.lessandro.model.MedicalEquipment;
import br.com.lessandro.repository.IMedicalEquipmentRepository;
import br.com.lessandro.resources.exception.ValidationException;
import br.com.lessandro.service.IMedicalEquipmentService;
import br.com.lessandro.validator.PageValidator;

@Service
public class MedicalEquipmentService implements IMedicalEquipmentService {

	@Autowired
	private IMedicalEquipmentRepository medicalEquipmentRepository;

	@Autowired
	private ModelMapper modelMapper;


	@Override
	public PageDto<MedicalEquipmentDto> getAllMedicals(int page, int size) throws ValidationException {
		PageValidator.validatePageSize(page, size);
		Page<MedicalEquipment> medicals = new PageImpl<>(new ArrayList<>());
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "creationDate");
		medicals = medicalEquipmentRepository.findAll(pageable);
		List<MedicalEquipmentDto> adapatationsDto = Arrays
				.asList(modelMapper.map(medicals.getContent(), MedicalEquipmentDto[].class));
		return new PageDto<>(adapatationsDto, medicals.getNumber(), medicals.getSize(),
				medicals.getTotalElements(), medicals.getTotalPages(), medicals.isLast());
	}
	
	@Override
	public MedicalEquipment findByDescription(String description) {
		return medicalEquipmentRepository.findByDescription(description);
	}
	
	@Override
	public MedicalEquipment prepareMedicalEquipment(MedicalEquipment medicalEquipment) {
		if (StringUtils.isNotEmpty(medicalEquipment.getDescription())) {
			MedicalEquipment medicalEquipmentDB = this.findByDescription(medicalEquipment.getDescription());
			if (medicalEquipmentDB != null) {
				return medicalEquipmentDB;
			}
		} 
		return medicalEquipment;
	}

}
