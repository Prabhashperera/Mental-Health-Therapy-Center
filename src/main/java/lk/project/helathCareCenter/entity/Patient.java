package lk.project.helathCareCenter.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany (mappedBy = "patient" , cascade = CascadeType.ALL)
    private List<ProgramDetails> programDetails = new ArrayList<>();

    @OneToMany (mappedBy = "patientID" , cascade = CascadeType.ALL)
    private List<TherapySession> therapySessions = new ArrayList<>();
}
