package lk.project.healthCareCenter.dao;

import lk.project.healthCareCenter.dto.CustomProgramDetailsDTO;
import lk.project.healthCareCenter.dto.CustomTherapistDetailsDTO;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SessionBookingDAO {
    String generateNextSessionID(Session session) throws SQLException;
    ArrayList<CustomProgramDetailsDTO> loadPatientTable(Session session);
    ArrayList<CustomTherapistDetailsDTO> loadTherapistTable(Session session, String patientProgramID);
}
