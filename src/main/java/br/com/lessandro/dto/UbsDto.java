package br.com.lessandro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of = { "name", "codCnes" }, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UbsDto {

	private Long id;
	private String name;
	private Integer codCnes;
	private String phone;
	private AddressDto address;
	private ScoreDto score;
	private GeocodeDto geocode;

}
