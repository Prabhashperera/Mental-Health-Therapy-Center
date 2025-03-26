package lk.project.healthCareCenter.dao;

import lk.project.healthCareCenter.dto.ProgramDetailsDTO;
import lk.project.healthCareCenter.dto.TherapistDetailsDTO;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SessionBookingDAO {
    String generateNextSessionID(Session session) throws SQLException;
    ArrayList<ProgramDetailsDTO> loadPatientTable(Session session);
    ArrayList<TherapistDetailsDTO> loadTherapistTable(Session session);
}
