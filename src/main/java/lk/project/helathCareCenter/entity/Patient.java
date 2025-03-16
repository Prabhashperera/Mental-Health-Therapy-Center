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
    private int patientAge;
    private String mobileNumber;
    private String medicalHistory;

    @OneToMany (mappedBy = "patient" , cascade = CascadeType.ALL)
    private List<ProgramDetails> programDetails = new ArrayList<>();

    @OneToMany (mappedBy = "patient" , cascade = CascadeType.ALL)
    private List<TherapySession> therapySessions = new ArrayList<>();

    @OneToMany (mappedBy = "patient" , cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    public Patient(String id, String name, String age, String number, String note) {
        this.patientID = id;
        this.patientName = name;
        this.patientAge = Integer.parseInt(age);
        this.mobileNumber = number;
        this.medicalHistory = note;
    }
}
