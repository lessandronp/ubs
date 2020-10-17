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

import br.com.lessandro.dto.MedicineDto;
import br.com.lessandro.dto.PageDto;
import br.com.lessandro.model.Medicine;
import br.com.lessandro.repository.IMedicineRepository;
import br.com.lessandro.resources.exception.ValidationException;
import br.com.lessandro.service.IMedicineService;
import br.com.lessandro.validator.PageValidator;

@Service
public class MedicineService implements IMedicineService {

	@Autowired
	private IMedicineRepository medicineRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PageDto<MedicineDto> getAllMedicines(int page, int size) throws ValidationException {
		PageValidator.validatePageSize(page, size);
		Page<Medicine> medicines = new PageImpl<>(new ArrayList<>());
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "creationDate");
		medicines = medicineRepository.findAll(pageable);
		List<MedicineDto> adapatationsDto = Arrays
				.asList(modelMapper.map(medicines.getContent(), MedicineDto[].class));
		return new PageDto<>(adapatationsDto, medicines.getNumber(), medicines.getSize(),
				medicines.getTotalElements(), medicines.getTotalPages(), medicines.isLast());
	}
	
	@Override
	public Medicine findByDescription(String description) {
		return medicineRepository.findByDescription(description);
	}
	
	@Override
	public Medicine prepareMedicine(Medicine medicine) {
		if (StringUtils.isNotEmpty(medicine.getDescription())) {
			Medicine medicineDB = this.findByDescription(medicine.getDescription());
			if (medicineDB != null) {
				return medicineDB;
			}
		} 
		return medicine;
	}

}
