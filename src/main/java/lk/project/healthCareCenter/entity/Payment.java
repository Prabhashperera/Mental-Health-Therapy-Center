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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PaymentID;
    private String paymentDate;
    private int paymentAmount;

    @ManyToOne
    @JoinColumn (name = "patientID")
    private Patient patient;

    @ManyToOne
    @JoinColumn (name = "programID")
    private TherapyProgram therapyProgram;

    public Payment(String date, int programFee, Patient patient, TherapyProgram therapyProgram) {
        this.paymentDate = date;
        this.paymentAmount = programFee;
        this.patient = patient;
        this.therapyProgram = therapyProgram;
    }
}
