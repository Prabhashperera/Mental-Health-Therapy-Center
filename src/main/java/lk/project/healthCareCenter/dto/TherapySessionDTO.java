package lk.project.healthCareCenter.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter

public class TherapySessionDTO {
    private String sessionID;
    private String sessionDate;
    private String sessionTime;
    private String therapistID;
    private String patientID;
}
