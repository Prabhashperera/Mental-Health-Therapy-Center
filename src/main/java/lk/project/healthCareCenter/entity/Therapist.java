package lk.project.healthCareCenter.entity;


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

    @OneToOne
    @JoinColumn(name = "programID")
    private TherapyProgram therapyProgram;

    @OneToMany (mappedBy = "therapist" , cascade = CascadeType.ALL)
    private List<TherapySession> therapySessions = new ArrayList<>();

    public Therapist(String id, String name, TherapyProgram program) {
        therapistID = id;
        therapistName = name;
        therapyProgram = program;
    }
}
