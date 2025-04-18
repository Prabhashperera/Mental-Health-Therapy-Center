package lk.project.healthCareCenter.bo;

import lk.project.healthCareCenter.dto.CustomProgramDetailsDTO;
import lk.project.healthCareCenter.dto.CustomTherapistDetailsDTO;
import lk.project.healthCareCenter.dto.TherapySessionDTO;
import lk.project.healthCareCenter.entity.TherapySession;

import java.util.ArrayList;

public interface SessionBookingBO {
    String generateNextSessionID();
    ArrayList<CustomProgramDetailsDTO> loadPatientTable();
    ArrayList<CustomTherapistDetailsDTO> loadTherapistTable(String patientProgramID , String selectedDateLabel, String selectedTimeLabel);
    boolean saveSession(TherapySessionDTO sessionDTO);
    ArrayList<TherapySessionDTO> showAllBookingsTable();
    boolean updateBooking(TherapySessionDTO sessionDto);
    boolean deleteBooking(String sessionID);
}
