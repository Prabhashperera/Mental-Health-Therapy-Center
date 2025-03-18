package lk.project.healthCareCenter.bo.impl;

import lk.project.healthCareCenter.bo.RegisterPageBO;
import lk.project.healthCareCenter.dao.RegisterPageDAO;
import lk.project.healthCareCenter.dao.impl.RegisterPageDAOImpl;
import lk.project.healthCareCenter.entity.Patient;
import lk.project.healthCareCenter.entity.TherapyProgram;
import lk.project.healthCareCenter.hibernateConfig.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterPageBOImpl implements RegisterPageBO {
    private RegisterPageDAO registerDAO = new RegisterPageDAOImpl();

    @Override
    public boolean savePatient(Patient patient) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            boolean isSavedPatient = registerDAO.savePatient(patient , session);
            if (isSavedPatient) {
                boolean isSavedProgramDetails = registerDAO.saveProgramDetails(session, patient);
                if (isSavedProgramDetails) {
                    transaction.commit();
                    return true;
                }else {
                    transaction.rollback();
                    System.out.println("TherapyProgram Not Saved");
                }
            }
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return false;

    }

    @Override
    public String generateNextID() throws SQLException {
        return registerDAO.generateNextID();
    }

    @Override
    public ArrayList<Patient> loadPatientTable() throws SQLException {
        return registerDAO.loadPatientTable();
    }

    @Override
    public ArrayList<TherapyProgram> loadProgramTable() throws SQLException {
        return registerDAO.loadProgramTable();
    }

    @Override
    public boolean updatePatient(Patient patient) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction tx = session.beginTransaction();
        try {
            boolean isUpdatedPatient = registerDAO.updatePatient(patient, session);
            if (isUpdatedPatient) {
                boolean isUpdatedProgramDetails = registerDAO.updateProgramDetails(session, patient);
                if (isUpdatedProgramDetails) {
                    tx.commit();
                    return true;
                }
            }
        }
        catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean deletePatient(String patient) {
        return registerDAO.deletePatient(patient);
    }
}
