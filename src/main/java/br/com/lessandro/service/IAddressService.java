package br.com.lessandro.service;

import br.com.lessandro.dto.AddressDto;
import br.com.lessandro.dto.PageDto;
import br.com.lessandro.model.Address;
import br.com.lessandro.resources.exception.ValidationException;

public interface IAddressService {

	PageDto<AddressDto> getAllAddress(int page, int size) throws ValidationException;
	Address findByStreetAndNeighborhood(String street, String neighborhood);
	Address prepareAddress(Address address);

}