package lk.project.healthCareCenter.entity;


import jakarta.persistence.*;

@Entity
public class ProgramDetails {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn (name = "patientID")
    private Patient patient;

    @ManyToOne
    @JoinColumn (name = "programID")
    private TherapyProgram therapyProgram;

}
