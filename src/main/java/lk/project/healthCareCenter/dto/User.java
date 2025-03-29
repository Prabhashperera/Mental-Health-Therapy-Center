package lk.project.healthCareCenter.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

public class User {
    private String userName;
    private String password;
    private String userRole;
}
