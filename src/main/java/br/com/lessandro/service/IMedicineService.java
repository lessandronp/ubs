package br.com.lessandro.service;

import br.com.lessandro.dto.MedicineDto;
import br.com.lessandro.dto.PageDto;
import br.com.lessandro.model.Medicine;
import br.com.lessandro.resources.exception.ValidationException;

public interface IMedicineService {

	PageDto<MedicineDto> getAllMedicines(int page, int size) throws ValidationException;

	Medicine findByDescription(String description);

	Medicine prepareMedicine(Medicine medicine);

}