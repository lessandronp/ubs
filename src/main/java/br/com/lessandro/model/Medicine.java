package br.com.lessandro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(of = { "description" }, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicine", uniqueConstraints = {
		@UniqueConstraint(name = "uk_medicine", columnNames = { "description" }) })
public class Medicine extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "S_MEDICINE_GENERATOR")
	@SequenceGenerator(name = "S_MEDICINE_GENERATOR", sequenceName = "SEQ_MEDICINE", initialValue = 1)
	@Column(name = "medicine_id", nullable = false)
	private Long id;
	
	@NotBlank
	@Column(name = "description", nullable = false)
	private String description;
	
}
