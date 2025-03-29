package lk.project.healthCareCenter.dto;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data

public class TherapyProgram {
    private String programID;
    private String programName;
    private String programDuration;
    private String programFee;}
