package lk.project.healthCareCenter.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

public class PaymentDTO {
    private String paymentID;
    private String paymentDate;
    private int paymentAmount;

}
