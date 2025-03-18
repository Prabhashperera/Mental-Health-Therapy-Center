package lk.project.healthCareCenter.bo;

import lk.project.healthCareCenter.entity.Patient;
import lk.project.healthCareCenter.entity.TherapyProgram;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RegisterPageBO {
    boolean savePatient(Patient patient);
    String generateNextID() throws SQLException;
    ArrayList<Patient> loadPatientTable() throws SQLException;
    ArrayList<TherapyProgram> loadProgramTable() throws SQLException;
    boolean updatePatient(Patient patient);
    boolean deletePatient(String patient);
}
