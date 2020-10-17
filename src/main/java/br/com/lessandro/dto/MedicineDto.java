package br.com.lessandro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of = { "description" }, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDto {

	private Long id;
	private String description;

}
