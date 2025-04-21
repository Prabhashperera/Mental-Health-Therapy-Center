package lk.project.healthCareCenter.dao.impl;

import lk.project.healthCareCenter.dao.PaymentDAO;
import lk.project.healthCareCenter.entity.Patient;
import lk.project.healthCareCenter.entity.Payment;
import lk.project.healthCareCenter.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public double checkProgramPayments(String programID, Session session) {
        TherapyProgram therapyProgram = session.get(TherapyProgram.class, programID);
        if (therapyProgram != null) {
            double programFee = Integer.parseInt(therapyProgram.getProgramFee());
            String weeks = therapyProgram.getProgramDuration();
            String number = weeks.split(" ")[0];
            int weekCount = Integer.parseInt(number);
            programFee = programFee / weekCount;
            return programFee;
        }
        return 0;
    }

    @Override
    public boolean savePayment(String date, String toPayAmount, String patientID, String programID, Session session) {
        try {
            TherapyProgram therapyProgram = session.get(TherapyProgram.class, programID);
            Patient patient = session.get(Patient.class, patientID);
//            int programFee = Integer.parseInt(toPayAmount);
            double temp = Double.parseDouble(toPayAmount);
            int programFee = (int) temp;
            Payment payment = new Payment(date, programFee, patient , therapyProgram);
            session.persist(payment);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int checkAvailableWeeks(String patientID, String programID, Session session) {
        String hql = "SELECT COUNT(p) FROM Payment p WHERE p.therapyProgram.programID = :programID AND p.patient.patientID = :patientID";
        System.out.println(patientID);
        System.out.println(programID);
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("programID", programID);
        query.setParameter("patientID", patientID);
        TherapyProgram therapyProgram = session.get(TherapyProgram.class, programID);
        String weeksString = therapyProgram.getProgramDuration();
        int weekCount = Integer.parseInt(weeksString.split(" ")[0]);
        int payedDays = Math.toIntExact((Long) query.getSingleResult());
        int returnCount = weekCount - payedDays;
        System.out.println(weekCount + " - " + payedDays);
        System.out.println(returnCount + " all");
        return returnCount;
    }
}
