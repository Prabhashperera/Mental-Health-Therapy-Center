package lk.project.healthCareCenter.entity;

import jakarta.persistence.*;
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
