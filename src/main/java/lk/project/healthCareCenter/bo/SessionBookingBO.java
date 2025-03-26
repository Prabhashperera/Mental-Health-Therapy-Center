package lk.project.healthCareCenter.bo;

import lk.project.healthCareCenter.dto.ProgramDetailsDTO;
import lk.project.healthCareCenter.dto.TherapistDetailsDTO;

import java.util.ArrayList;

public interface SessionBookingBO {
    String generateNextSessionID();
    ArrayList<ProgramDetailsDTO> loadPatientTable();
    ArrayList<TherapistDetailsDTO> loadTherapistTable();
}
