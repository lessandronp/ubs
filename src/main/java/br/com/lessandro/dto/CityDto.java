package br.com.lessandro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of = { "name", "code" }, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {

	private Long id;
	private Integer code;
	private String name;

}
