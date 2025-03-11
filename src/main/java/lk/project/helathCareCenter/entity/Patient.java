package lk.project.helathCareCenter.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data

@Entity
public class Patient {
    @Id
    private String patientID;
    private String patientName;
    private String patientAge;
    private String mobileNumber;
    private String medicalHistory;
}
