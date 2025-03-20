package lk.project.healthCareCenter.bo.impl;

import lk.project.healthCareCenter.bo.TherapistManageBO;
import lk.project.healthCareCenter.dao.TherapistManageDAO;
import lk.project.healthCareCenter.dao.impl.TherapistManageDAOImpl;
import lk.project.healthCareCenter.entity.Therapist;
import lk.project.healthCareCenter.hibernateConfig.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TherapistManageBOImpl implements TherapistManageBO {

    private final TherapistManageDAO therapistManageDAO = new TherapistManageDAOImpl();

    @Override
    public boolean saveTherapist(Therapist therapist) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction tx = session.beginTransaction();
        try {
            boolean isSaved = therapistManageDAO.saveTherapist(therapist , session);
            if (isSaved) {
                tx.commit();
                return true;
            }
        }catch (Exception e) {
            tx.rollback();
            return false;
        }finally {
            session.close();
        }
        return false;
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
