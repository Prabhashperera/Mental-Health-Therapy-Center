package lk.project.healthCareCenter.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Embeddable
public class ProgramDetailsId implements Serializable {

    @Column(name = "patientID")
    private String patientID;

    @Column(name = "programID")
    private String programID;

    // Default constructor
    public ProgramDetailsId() {}

    public ProgramDetailsId(String patientID , String programID) {
        this.patientID = patientID;
        this.programID = programID;
    }

    // Getters, Setters, equals() & hashCode()
}

