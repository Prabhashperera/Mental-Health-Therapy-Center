package lk.project.healthCareCenter.dto;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@Data

public class ProgramDetailsDTO {
    private String programID;
    private String programName;
    private String patientID;
    private String patientName;

    public ProgramDetailsDTO(String patientID, String patientName, String programID, String programName) {
        this.patientID = patientID;
        this.patientName = patientName;
        this.programID = programID;
        this.programName = programName;
    }
}
