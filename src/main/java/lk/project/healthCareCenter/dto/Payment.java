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

public class Payment {
    private String paymentID;
    private String paymentDate;
    private int paymentAmount;

}
