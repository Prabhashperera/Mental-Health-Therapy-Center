package lk.project.healthCareCenter.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

public class UserDTO {
    private String userName;
    private String password;
    private String userRole;
}
