package lk.project.healthCareCenter.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

@Entity
public class Payment {
    @Id
    private String paymentID;
    private String paymentDate;
    private int paymentAmount;

    @ManyToOne
    @JoinColumn (name = "patientID")
    private Patient patient;

    @ManyToOne
    @JoinColumn (name = "programID")
    private TherapyProgram therapyProgram;

}
