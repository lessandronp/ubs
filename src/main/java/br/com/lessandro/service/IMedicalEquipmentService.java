package br.com.lessandro.service;

import br.com.lessandro.dto.MedicalEquipmentDto;
import br.com.lessandro.dto.PageDto;
import br.com.lessandro.model.MedicalEquipment;
import br.com.lessandro.resources.exception.ValidationException;

public interface IMedicalEquipmentService {

	PageDto<MedicalEquipmentDto> getAllMedicals(int page, int size) throws ValidationException;

	MedicalEquipment findByDescription(String description);

	MedicalEquipment prepareMedicalEquipment(MedicalEquipment medicalEquipment);

}