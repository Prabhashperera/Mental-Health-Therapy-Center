package lk.project.healthCareCenter.bo;

import lk.project.healthCareCenter.dto.CustomProgramDetailsDTO;
import lk.project.healthCareCenter.dto.CustomTherapistDetailsDTO;
import lk.project.healthCareCenter.dto.TherapySessionDTO;

import java.util.ArrayList;

public interface SessionBookingBO {
    String generateNextSessionID();
    ArrayList<CustomProgramDetailsDTO> loadPatientTable();
    ArrayList<CustomTherapistDetailsDTO> loadTherapistTable(String patientProgramID);
    boolean saveSession(TherapySessionDTO sessionDTO);
}
