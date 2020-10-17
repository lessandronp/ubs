package br.com.lessandro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(of = { "latitude", "longitude" }, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "geocode", uniqueConstraints = {
		@UniqueConstraint(name = "uk_geocode", columnNames = { "latitude", "longitude" }) })
public class Geocode extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "S_GEOCODE_GENERATOR")
	@SequenceGenerator(name = "S_GEOCODE_GENERATOR", sequenceName = "SEQ_GEOCODE", initialValue = 1)
	@Column(name = "geocode_id", nullable = false)
	private Long id;

	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;

}
