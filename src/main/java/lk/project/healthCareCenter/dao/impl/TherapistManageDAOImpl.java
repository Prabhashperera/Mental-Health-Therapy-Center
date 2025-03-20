package lk.project.healthCareCenter.dao.impl;

import lk.project.healthCareCenter.dao.TherapistManageDAO;
import lk.project.healthCareCenter.entity.Therapist;
import org.hibernate.Session;

public class TherapistManageDAOImpl implements TherapistManageDAO {
    @Override
    public boolean saveTherapist(Therapist therapist, Session session) {
        try {
            session.persist(therapist);
            session.flush();
            session.clear();
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateTherapist(Therapist therapist) {
        return false;
    }

    @Override
    public boolean deleteTherapist(Therapist therapist) {
        return false;
    }
}
