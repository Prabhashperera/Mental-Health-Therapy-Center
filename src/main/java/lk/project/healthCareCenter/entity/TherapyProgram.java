package lk.project.healthCareCenter.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data

@Entity
public class TherapyProgram {
    @Id
    private String programId;
    private String programName;
    private String programDuration;
    private String programFee;

    @OneToMany (mappedBy = "therapyProgram" , cascade = CascadeType.ALL)
    private List<ProgramDetails> programDetails = new ArrayList<>();
}
