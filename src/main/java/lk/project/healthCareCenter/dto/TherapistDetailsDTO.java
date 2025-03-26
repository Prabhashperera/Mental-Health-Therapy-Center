package lk.project.healthCareCenter.dto;

import lk.project.healthCareCenter.entity.Therapist;

public class TherapistDetailsDTO {
    private Therapist therapistID;
    private Therapist therapistName;
    private Therapist programID;
    private Therapist programName;

    public TherapistDetailsDTO() {}

    public TherapistDetailsDTO(Therapist therapistID, Therapist therapistName, Therapist programID, Therapist programName) {
        this.therapistID = therapistID;
        this.therapistName = therapistName;
        this.programID = programID;
        this.programName = programName;
    }

    public Therapist getTherapistID() {
        return therapistID;
    }

    public void setTherapistID(Therapist therapistID) {
        this.therapistID = therapistID;
    }

    public Therapist getTherapistName() {
        return therapistName;
    }

    public void setTherapistName(Therapist therapistName) {
        this.therapistName = therapistName;
    }

    public Therapist getProgramID() {
        return programID;
    }

    public void setProgramID(Therapist programID) {
        this.programID = programID;
    }

    public Therapist getProgramName() {
        return programName;
    }

    public void setProgramName(Therapist programName) {
        this.programName = programName;
    }
}
