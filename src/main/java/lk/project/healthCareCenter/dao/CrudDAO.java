package lk.project.healthCareCenter.dao;

import lk.project.healthCareCenter.entity.Patient;
import org.hibernate.Session;

import java.sql.SQLException;

public interface CrudDAO {
    String generateNextID() throws SQLException;
    boolean savePatient(Patient patient, Session session);
    boolean deletePatient(String patient);
    boolean updatePatient(Patient patient , Session session);
}
