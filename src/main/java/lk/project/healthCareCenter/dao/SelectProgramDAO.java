package lk.project.healthCareCenter.dao;

import org.hibernate.Session;

import java.util.List;

public interface SelectProgramDAO {
    boolean saveProgramDetails(String patientID , String programID, Session session);
    List<Object[]> loadProgramDetails(Session session);
    boolean updateProgram(String patient, String program, String clickedPatientID, String clickedProgramID, Session session);
    boolean deleteProgramDetail(String patient, String program, Session session);
}
