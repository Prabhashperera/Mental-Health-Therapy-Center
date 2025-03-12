package lk.project.helathCareCenter.entity;

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
    String paymentID;
    String paymentDate;
    String PaymentAmount;

    @ManyToOne
    @JoinColumn (name = "patientID")
    private Patient patient;

}
