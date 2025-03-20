package lk.project.healthCareCenter.dao;

import lk.project.healthCareCenter.entity.Therapist;
import org.hibernate.Session;

public interface TherapistManageDAO {
    boolean saveTherapist(Therapist therapist, Session session);
    boolean updateTherapist(Therapist therapist);
    boolean deleteTherapist(Therapist therapist);
}
