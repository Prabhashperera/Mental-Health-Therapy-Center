package lk.project.healthCareCenter.bo.impl;

import javafx.collections.FXCollections;
import lk.project.healthCareCenter.bo.SessionBookingBO;
import lk.project.healthCareCenter.dao.SessionBookingDAO;
import lk.project.healthCareCenter.dao.impl.SessionBookingDAOImpl;
import lk.project.healthCareCenter.dto.CustomProgramDetailsDTO;
import lk.project.healthCareCenter.dto.CustomTherapistDetailsDTO;
import lk.project.healthCareCenter.dto.TherapySessionDTO;
import lk.project.healthCareCenter.entity.Patient;
import lk.project.healthCareCenter.entity.Therapist;
import lk.project.healthCareCenter.entity.TherapySession;
import lk.project.healthCareCenter.hibernateConfig.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class SessionBookingBOImpl implements SessionBookingBO{
    private final SessionBookingDAO sessionBookingDAO = new SessionBookingDAOImpl();

    @Override
    public String generateNextSessionID() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        try {
            String generatedSessionID = sessionBookingDAO.generateNextSessionID(session);
            if (generatedSessionID != null) {
                return generatedSessionID;
            }
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public ArrayList<CustomProgramDetailsDTO> loadPatientTable() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        return sessionBookingDAO.loadPatientTable(session);
    }

    @Override
    public ArrayList<CustomTherapistDetailsDTO> loadTherapistTable(String patientProgramID, String selectedDateLabel, String selectedTimeLabel) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        return sessionBookingDAO.loadTherapistTable(session , patientProgramID, selectedDateLabel, selectedTimeLabel);
    }

    @Override
    public boolean saveSession(TherapySessionDTO sessionDTO) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Therapist therapist = session.get(Therapist.class, sessionDTO.getTherapistID());
            Patient patient = session.get(Patient.class, sessionDTO.getPatientID());
            TherapySession therapySession = new TherapySession(sessionDTO.getSessionID(), sessionDTO.getSessionDate(), sessionDTO.getSessionTime(), therapist, patient);
            boolean isSaved = sessionBookingDAO.saveSession(therapySession, session);
            if (isSaved) {
                transaction.commit();
                System.out.println("Saved Session");
            }else {
                transaction.rollback();
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<TherapySessionDTO> showAllBookingsTable() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        ArrayList<TherapySession> list = sessionBookingDAO.showAllBookingsTable(session);
        ArrayList<TherapySessionDTO> data = new ArrayList<>();

        for (TherapySession sessionEntity : list) {
            TherapySessionDTO therapySessionDTO = new TherapySessionDTO();
            therapySessionDTO.setSessionID(sessionEntity.getSessionID());
            therapySessionDTO.setSessionDate(sessionEntity.getSessionDate());
            therapySessionDTO.setSessionTime(sessionEntity.getSessionTime());
            therapySessionDTO.setPatientID(sessionEntity.getPatient().getPatientID());
            therapySessionDTO.setTherapistID(sessionEntity.getTherapist().getTherapistID());
            data.add(therapySessionDTO);
        }

        return data;
    }

}
