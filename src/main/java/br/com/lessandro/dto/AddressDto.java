package br.com.lessandro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of = { "street", "neighborhood", "city" }, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

	private Long id;
	private String street;
	private String neighborhood;
	private CityDto city;

}
