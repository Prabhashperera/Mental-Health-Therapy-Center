package lk.project.healthCareCenter.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class ProgramDetails {
    @EmbeddedId  // Use composite key
    private ProgramDetailsId id;

    @ManyToOne
    @MapsId("patientID")
    @JoinColumn (name = "patientID")
    private Patient patient;

    @ManyToOne
    @MapsId("programID")
    @JoinColumn (name = "programID")
    private TherapyProgram therapyProgram;

}
