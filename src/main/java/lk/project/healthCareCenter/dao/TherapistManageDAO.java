package lk.project.healthCareCenter.dao;

import lk.project.healthCareCenter.entity.Therapist;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TherapistManageDAO {
    boolean saveTherapist(Therapist therapist, Session session);
    boolean updateTherapist(Therapist therapist, Session session);
    boolean deleteTherapist(Therapist therapist);
    String generateNextID(Session session) throws SQLException;
    ArrayList<Therapist> loadTherapistTable(Session session);
}
