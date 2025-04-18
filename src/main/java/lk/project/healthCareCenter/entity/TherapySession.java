package lk.project.healthCareCenter.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter

@Entity
public class TherapySession {
    @Id
    private String sessionID;
    private String sessionDate;
    private String sessionTime;

    @ManyToOne
    @JoinColumn (name = "therapistID")
    private Therapist therapist;

    @ManyToOne
    @JoinColumn (name = "patientID")
    private Patient patient;

    public TherapySession(String sessionID, String sessionDate, String sessionTime, String patientID, String therapistID) {
        sessionID = sessionID;
        sessionDate = sessionDate;
        sessionTime = sessionTime;
        patientID = patientID;
        therapistID = therapistID;
    }
}
