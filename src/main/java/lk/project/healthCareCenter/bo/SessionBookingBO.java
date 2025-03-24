package lk.project.healthCareCenter.bo;

import lk.project.healthCareCenter.dto.ProgramDetailsDTO;

import java.util.ArrayList;

public interface SessionBookingBO {
    String generateNextSessionID();
    ArrayList<ProgramDetailsDTO> loadPatientTable();
}
