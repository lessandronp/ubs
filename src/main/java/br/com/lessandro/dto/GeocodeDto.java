package br.com.lessandro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of = { "latitude", "longitude" }, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class GeocodeDto {

	private Long id;
	private String latitude;
	private String longitude;

}
