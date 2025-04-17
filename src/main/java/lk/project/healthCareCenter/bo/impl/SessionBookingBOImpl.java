package lk.project.healthCareCenter.bo.impl;

import lk.project.healthCareCenter.bo.SessionBookingBO;
import lk.project.healthCareCenter.dao.SessionBookingDAO;
import lk.project.healthCareCenter.dao.impl.SessionBookingDAOImpl;
import lk.project.healthCareCenter.dto.CustomProgramDetailsDTO;
import lk.project.healthCareCenter.dto.CustomTherapistDetailsDTO;
import lk.project.healthCareCenter.hibernateConfig.FactoryConfiguration;
import org.hibernate.Session;

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
    public ArrayList<CustomTherapistDetailsDTO> loadTherapistTable(String patientProgramID) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        return sessionBookingDAO.loadTherapistTable(session , patientProgramID);
    }
}
