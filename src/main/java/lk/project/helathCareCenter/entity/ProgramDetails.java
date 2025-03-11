package lk.project.helathCareCenter.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ProgramDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToMany
    @JoinColumn (name = "patientID")
    private Patient patient;

    @ManyToMany
    @JoinColumn (name = "programID")
    private TherapyProgram therapyProgram;

}
