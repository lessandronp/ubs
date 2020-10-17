package br.com.lessandro.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.lessandro.dto.AddressDto;
import br.com.lessandro.dto.PageDto;
import br.com.lessandro.model.Address;
import br.com.lessandro.repository.IAddressRepository;
import br.com.lessandro.resources.exception.ValidationException;
import br.com.lessandro.service.IAddressService;
import br.com.lessandro.service.ICityService;
import br.com.lessandro.validator.PageValidator;

@Service
public class AddressService implements IAddressService {

	@Autowired
	private IAddressRepository addressRepository;
	
	@Autowired
	private ICityService cityService;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PageDto<AddressDto> getAllAddress(int page, int size) throws ValidationException {
		PageValidator.validatePageSize(page, size);
		Page<Address> addresses = new PageImpl<>(new ArrayList<>());
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "creationDate");
		addresses = addressRepository.findAll(pageable);
		List<AddressDto> adapatationsDto = Arrays
				.asList(modelMapper.map(addresses.getContent(), AddressDto[].class));
		return new PageDto<>(adapatationsDto, addresses.getNumber(), addresses.getSize(),
				addresses.getTotalElements(), addresses.getTotalPages(), addresses.isLast());
	}

	@Override
	public Address findByStreetAndNeighborhood(String street, String neighborhood) {
		return addressRepository.findByStreetAndNeighborhood(street, neighborhood);
	}
	

	@Override
	public Address prepareAddress(Address address) {
		Address addressDB = this.findByStreetAndNeighborhood(address.getStreet(), address.getNeighborhood());
		if (addressDB != null) {
			return addressDB;
		} else {
			address.setCity(cityService.prepareCity(address.getCity()));
		}
		return address;
	}

}
