package lk.project.healthCareCenter.dto;

import lk.project.healthCareCenter.entity.Therapist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapistDetailsDTO {
    private String therapistID;
    private String therapistName;
    private String programID;
    private String programName;

}
