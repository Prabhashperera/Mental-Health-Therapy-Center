package lk.project.healthCareCenter.dao.impl;

import lk.project.healthCareCenter.dao.RegisterPageDAO;
import lk.project.healthCareCenter.entity.Patient;
import lk.project.healthCareCenter.hibernateConfig.FactoryConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterPageDAOImpl implements RegisterPageDAO {
    @Override
    public boolean savePatient(Patient patient) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(patient);
            tx.commit();
            session.close();
            System.out.println("Save patient successful");
        }catch(Exception e) {
            tx.rollback();
            System.out.println(e.getMessage());
            System.out.println("awl");
            return false;
        }
        return true;
    }

    @Override
    public boolean updatePatient(Patient patient) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction tx = session.beginTransaction();
        try {
            Patient updatePatient = session.get(Patient.class , patient.getPatientID());
            updatePatient.setPatientName(patient.getPatientName());
            updatePatient.setPatientID(patient.getPatientID());
            updatePatient.setPatientAge(patient.getPatientAge());
            updatePatient.setMobileNumber(updatePatient.getMobileNumber());
            updatePatient.setMedicalHistory(updatePatient.getMedicalHistory());
            session.merge(updatePatient); //Merge Updated Patient
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            tx.rollback();
            System.out.println(e.getMessage());
            return false;
        }finally {
            session.close();
        }
        return true;
    }

    @Override
    public String generateNextID() throws SQLException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        String hql = "SELECT f.patientID FROM Patient f ORDER BY f.patientID DESC";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        String lastPatient = (String) query.uniqueResult();
        if (lastPatient != null && lastPatient.length() > 1) {
            // Extract the numeric part, make sure there is at least one character after 'C'
            String substring = lastPatient.substring(1); // Extract the numeric part (after 'C')
            try {
                int i = Integer.parseInt(substring); // Convert the numeric part to an integer
                int newIdIndex = i + 1; // Increment the number by 1
                return String.format("P%03d", newIdIndex); // Return the new customer ID in format Cnnn
            } catch (NumberFormatException e) {
                // Handle cases where the numeric part is invalid
                throw new SQLException("Invalid customer ID format in the database: " + lastPatient);
            } finally {
                session.close();
            }
        }
        return "P001";
    }

    @Override
    public ArrayList<Patient> loadTable() throws SQLException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        String hql = "FROM Patient";  // Assuming 'Patient' is your entity class name
        Query query = session.createQuery(hql);
        List<Patient> patients = query.getResultList();
        session.close();
        return new ArrayList<>(patients);
    }

}
