package lk.project.healthCareCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomTherapistDetailsDTO {
    private String therapistID;
    private String therapistName;
    private String programID;
    private String programName;

}
