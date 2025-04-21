package lk.project.healthCareCenter.dao;

import org.hibernate.Session;

public interface PaymentDAO {
    double checkProgramPayments(String therapistID, Session session);
    boolean savePayment(String date, String toPayAmount, String patientID, String programID, Session session);
    int checkAvailableWeeks(String patientID, String programID, Session session);
}
