package lk.project.healthCareCenter.dao;

import lk.project.healthCareCenter.entity.Patient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RegisterPageDAO {
    boolean savePatient(Patient patient);
    String generateNextID() throws SQLException;
    ArrayList<Patient> loadTable() throws SQLException;
    boolean updatePatient(Patient patient);
}
