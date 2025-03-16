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
}
