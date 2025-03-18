package lk.project.healthCareCenter.dao;

import lk.project.healthCareCenter.entity.Patient;
import lk.project.healthCareCenter.entity.TherapyProgram;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RegisterPageDAO {
    boolean savePatient(Patient patient, Session session);
    String generateNextID() throws SQLException;
    ArrayList<Patient> loadPatientTable() throws SQLException;
    ArrayList<TherapyProgram> loadProgramTable() throws SQLException;
    boolean updatePatient(Patient patient , Session session);
    boolean deletePatient(String patient);
    boolean saveProgramDetails(Session session, Patient patient);
    boolean updateProgramDetails(Session session, Patient patient);
}
