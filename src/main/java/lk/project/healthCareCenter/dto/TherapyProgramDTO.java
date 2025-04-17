package lk.project.healthCareCenter.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data

public class TherapyProgramDTO {
    private String programID;
    private String programName;
    private String programDuration;
    private String programFee;}
