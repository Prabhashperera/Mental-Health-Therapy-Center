package lk.project.healthCareCenter.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data

@Entity
@Table (name = "patient")
public class Patient {
    @Id
    private String patientID;
    private String patientName;
    private String patientAge;
    private String mobileNumber;
    private String medicalHistory;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "patient" , cascade = CascadeType.ALL)
    private List<ProgramDetails> programDetails = new ArrayList<>();

    @OneToMany (mappedBy = "patient" , cascade = CascadeType.ALL)
    private List<TherapySession> therapySessions = new ArrayList<>();

    @OneToMany (mappedBy = "patient" , cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    public Patient(String id, String name, String age, String number, String note) {
        this.patientID = id;
        this.patientName = name;
        this.patientAge = String.valueOf(Integer.parseInt(age));
        this.mobileNumber = number;
        this.medicalHistory = note;
    }
}
