package br.com.lessandro.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(of = { "street", "neighborhood", "city" }, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address", uniqueConstraints = {
		@UniqueConstraint(name = "uk_address", columnNames = { "street", "neighborhood", "city_id" }) })
public class Address extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "S_ADDRESS_GENERATOR")
	@SequenceGenerator(name = "S_ADDRESS_GENERATOR", sequenceName = "SEQ_ADDRESS", initialValue = 1)
	@Column(name = "address_id", nullable = false)
	private Long id;

	@Column(name = "street", nullable = false, length = 2048)
	private String street;

	@Column(name = "neighborhood", nullable = false, length = 1024)
	private String neighborhood;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "city_id")
	private City city;

}
