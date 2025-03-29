package lk.project.healthCareCenter.dto;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter

public class Therapist {
    private String therapistID;
    private String therapistName;
}
