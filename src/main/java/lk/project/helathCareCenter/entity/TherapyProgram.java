package lk.project.helathCareCenter.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
