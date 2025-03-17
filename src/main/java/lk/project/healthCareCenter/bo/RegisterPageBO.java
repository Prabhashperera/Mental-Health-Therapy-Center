package lk.project.healthCareCenter.bo;

import lk.project.healthCareCenter.entity.Patient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RegisterPageBO {
    boolean savePatient(Patient patient);
    String generateNextID() throws SQLException;
    ArrayList<Patient> loadTable() throws SQLException;
    boolean updatePatient(Patient patient);
}
