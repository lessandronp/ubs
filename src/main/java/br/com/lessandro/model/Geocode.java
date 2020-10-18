package br.com.lessandro.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(of = { "latitude", "longitude" }, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Geocode extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "S_GEOCODE_GENERATOR")
	@SequenceGenerator(name = "S_GEOCODE_GENERATOR", sequenceName = "SEQ_GEOCODE", initialValue = 1)
	@Column(name = "geocode_id", nullable = false)
	private Long id;

	@Column(name = "latitude", precision = 17, scale = 13)
	private BigDecimal latitude;

	@Column(name = "longitude", precision = 17, scale = 13)
	private BigDecimal longitude;

}
