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
@EqualsAndHashCode(of = { "name", "code" }, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "city", uniqueConstraints = { @UniqueConstraint(name = "uk_city", columnNames = { "code", "name" }) })
public class City extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "S_CITY_GENERATOR")
	@SequenceGenerator(name = "S_CITY_GENERATOR", sequenceName = "SEQ_CITY", initialValue = 1)
	@Column(name = "city_id", nullable = false)
	private Long id;

	@Column(name = "code", nullable = false)
	private Integer code;

	@NotBlank
	@Column(name = "name", nullable = false)
	private String name;

}
