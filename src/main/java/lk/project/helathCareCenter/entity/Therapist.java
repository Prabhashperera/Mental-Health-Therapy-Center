package lk.project.helathCareCenter.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter

@Entity
public class Therapist {
    @Id
    private String therapistID;
    private String therapistName;

    @OneToMany
    @JoinColumn(name = "programID")
    private TherapyProgram therapyProgram;

    @OneToMany (mappedBy = "therapistID" , cascade = CascadeType.ALL)
    private List<TherapySession> therapySessions = new ArrayList<>();

}
