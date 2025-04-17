package lk.project.healthCareCenter.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientDTO {
    private String patientID;
    private String patientName;
    private String patientAge;
    private String mobileNumber;
    private String medicalHistory;

}
