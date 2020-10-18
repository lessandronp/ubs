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
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(of = { "name", "codCnes" }, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ubs", uniqueConstraints = { @UniqueConstraint(name = "uk_ubs", columnNames = { "name", "cod_cnes" }) })
public class Ubs extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "S_UBS_GENERATOR")
	@SequenceGenerator(name = "S_UBS_GENERATOR", sequenceName = "SEQ_UBS", initialValue = 1)
	@Column(name = "ubs_id", nullable = false)
	private Long id;

	@NotBlank
	@Column(name = "name", nullable = false, length = 1024)
	private String name;

	@Column(name = "cod_cnes", nullable = false, length = 20)
	private Integer codCnes;

	@Column(name = "phone", length = 20)
	private String phone;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "address_id")
	private Address address;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "score_id")
	private Score score;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "geocode_id")
	private Geocode geocode;

}
