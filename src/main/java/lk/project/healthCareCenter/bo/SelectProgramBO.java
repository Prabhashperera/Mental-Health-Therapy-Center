package lk.project.healthCareCenter.bo;

import java.util.List;

public interface SelectProgramBO {
    boolean saveProgramDetails(String patientID , String programID);
    List<Object[]> getProgramDetails();
    boolean updateProgramDetail(String patient, String program, String clickedPatientID, String clickedProgramID);
}
