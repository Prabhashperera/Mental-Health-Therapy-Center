package lk.project.healthCareCenter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode @ToString

@Embeddable
public class ProgramDetailsId implements Serializable {

    @Column(name = "programID")
    private String programID;

    @Column(name = "patientID")
    private String patientID;

    // Default constructor
    public ProgramDetailsId() {}

    public ProgramDetailsId(String programID , String patientID) {
        this.programID = programID;
        this.patientID = patientID;
    }

    // Getters, Setters, equals() & hashCode()
}

