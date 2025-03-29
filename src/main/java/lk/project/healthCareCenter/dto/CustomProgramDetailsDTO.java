package lk.project.healthCareCenter.dto;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@Data

public class CustomProgramDetailsDTO {
    private String programID;
    private String programName;
    private String patientID;
    private String patientName;

    public CustomProgramDetailsDTO(String patientID, String patientName, String programID, String programName) {
        this.patientID = patientID;
        this.patientName = patientName;
        this.programID = programID;
        this.programName = programName;
    }
}
