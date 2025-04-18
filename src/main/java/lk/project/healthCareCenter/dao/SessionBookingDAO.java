package lk.project.healthCareCenter.dao;

import lk.project.healthCareCenter.dto.CustomProgramDetailsDTO;
import lk.project.healthCareCenter.dto.CustomTherapistDetailsDTO;
import lk.project.healthCareCenter.dto.TherapySessionDTO;
import lk.project.healthCareCenter.entity.TherapySession;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SessionBookingDAO {
    String generateNextSessionID(Session session) throws SQLException;
    ArrayList<CustomProgramDetailsDTO> loadPatientTable(Session session);
    ArrayList<CustomTherapistDetailsDTO> loadTherapistTable(Session session, String patientProgramID, String selectedDateLabel, String selectedTimeLabel);
    boolean saveSession(TherapySession sessionDTO, Session session);
    ArrayList<TherapySession> showAllBookingsTable(Session session);
    boolean updateBooking(Session session, TherapySessionDTO sessionDTO);
    boolean deleteBooking(Session session, String sessionID);
}
