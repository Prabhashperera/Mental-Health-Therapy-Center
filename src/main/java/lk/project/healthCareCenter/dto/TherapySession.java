package lk.project.healthCareCenter.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter

public class TherapySession {
    private String sessionID;
    private String sessionDate;
    private String sessionTime;
}
