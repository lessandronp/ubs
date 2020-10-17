package br.com.lessandro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of = { "structureSize" }, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDto {

	private Long id;
    private StructureSizeDto structureSize;
    private AdaptationSeniorDto adaptationSenior;
    private MedicalEquipmentDto medicalEquipment;
    private MedicineDto medicine;

}
