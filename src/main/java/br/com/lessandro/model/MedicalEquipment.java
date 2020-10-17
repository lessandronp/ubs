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
@Table(name = "medical_equipment", uniqueConstraints = {
		@UniqueConstraint(name = "uk_medicalEquipment", columnNames = { "description" }) })
public class MedicalEquipment extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "S_MED_EQUIP_GENERATOR")
	@SequenceGenerator(name = "S_MED_EQUIP_GENERATOR", sequenceName = "SEQ_MED_EQUIP", initialValue = 1)
	@Column(name = "medical_equipment_id", nullable = false)
	private Long id;

	@NotBlank
	@Column(name = "description", nullable = false)
	private String description;

}
