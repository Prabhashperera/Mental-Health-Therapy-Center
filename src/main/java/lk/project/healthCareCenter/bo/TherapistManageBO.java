package lk.project.healthCareCenter.bo;

import lk.project.healthCareCenter.entity.Therapist;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TherapistManageBO {
    boolean saveTherapist(Therapist therapist);
    boolean updateTherapist(Therapist therapist);
    boolean deleteTherapist(Therapist therapist);
    String generateNextID() throws SQLException;
    ArrayList<Therapist> loadTherapistTable();
}
