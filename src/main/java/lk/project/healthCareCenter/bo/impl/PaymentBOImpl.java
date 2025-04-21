package lk.project.healthCareCenter.bo.impl;

import lk.project.healthCareCenter.bo.PaymentBO;
import lk.project.healthCareCenter.dao.PaymentDAO;
import lk.project.healthCareCenter.dao.impl.PaymentDAOImpl;
import lk.project.healthCareCenter.hibernateConfig.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = new PaymentDAOImpl();

    @Override
    public double checkProgramPayments(String programIDLabel) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction tx = session.beginTransaction();
        double programFree = paymentDAO.checkProgramPayments(programIDLabel, session);
        return programFree;
    }

    @Override
    public boolean savePayment(String date, String toPayAmount, String patientID, String programID) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction tx = session.beginTransaction();
        try {
            boolean isSaved = paymentDAO.savePayment(date, toPayAmount, patientID, programID, session);
            tx.commit();
            return isSaved;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String checkAvailableWeeks(String patientID, String programID) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction tx = session.beginTransaction();
        int weeksCount = paymentDAO.checkAvailableWeeks(patientID, programID, session);
        tx.commit();
        return String.valueOf(weeksCount);
    }
}
