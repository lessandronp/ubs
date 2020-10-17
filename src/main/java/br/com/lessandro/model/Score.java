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
@EqualsAndHashCode(of = { "structureSize" }, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "score", uniqueConstraints = { @UniqueConstraint(name = "uk_score", columnNames = { "strucure_size_id",
		"adaptation_senior_id", "medical_equipment_id", "medicine_id" }) })
public class Score extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "S_SCORE_GENERATOR")
	@SequenceGenerator(name = "S_SCORE_GENERATOR", sequenceName = "SEQ_SCORE", initialValue = 1)
	@Column(name = "score_id", nullable = false)
	private Long id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "strucure_size_id")
	private StructureSize structureSize;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "adaptation_senior_id")
	private AdaptationSenior adaptationSenior;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "medical_equipment_id")
	private MedicalEquipment medicalEquipment;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "medicine_id")
	private Medicine medicine;

}
