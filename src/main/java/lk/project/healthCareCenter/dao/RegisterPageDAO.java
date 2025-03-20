package lk.project.healthCareCenter.dao;

import lk.project.healthCareCenter.entity.Patient;
import lk.project.healthCareCenter.entity.TherapyProgram;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RegisterPageDAO extends CrudDAO {
    ArrayList<Patient> loadPatientTable() throws SQLException;
    ArrayList<TherapyProgram> loadProgramTable() throws SQLException;
    boolean saveProgramDetails(Session session, Patient patient);
    boolean updateProgramDetails(Session session, Patient patient);
}
