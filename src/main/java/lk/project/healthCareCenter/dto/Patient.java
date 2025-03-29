package lk.project.healthCareCenter.dto;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient {
    private String patientID;
    private String patientName;
    private String patientAge;
    private String mobileNumber;
    private String medicalHistory;

}
