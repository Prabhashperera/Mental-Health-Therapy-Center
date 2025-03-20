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
    String paymentID;
    String paymentDate;
    int PaymentAmount;

    @ManyToOne
    @JoinColumn (name = "patientID")
    private Patient patient;

}
