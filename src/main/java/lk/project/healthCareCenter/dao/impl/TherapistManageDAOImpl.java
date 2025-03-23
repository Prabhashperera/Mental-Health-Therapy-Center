package lk.project.healthCareCenter.dao.impl;

import lk.project.healthCareCenter.dao.TherapistManageDAO;
import lk.project.healthCareCenter.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public boolean updateTherapist(Therapist therapist, Session session) {
        try {
            Therapist existTherapist = session.get(Therapist.class, therapist.getTherapistID());
            existTherapist.setTherapistName(therapist.getTherapistName());
            existTherapist.setTherapyProgram(therapist.getTherapyProgram());
            //set Sessions
            session.merge(existTherapist);
            session.flush();
            session.clear();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteTherapist(Therapist therapist) {
        return false;
    }

    @Override
    public String generateNextID(Session session) throws SQLException {
        String hql = "SELECT f.therapistID FROM Therapist f ORDER BY f.therapistID DESC";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        String lastTherpist = (String) query.uniqueResult();
        if (lastTherpist != null && lastTherpist.length() > 1) {
            // Extract the numeric part, make sure there is at least one character after 'C'
            String substring = lastTherpist.substring(1); // Extract the numeric part (after 'C')
            try {
                int i = Integer.parseInt(substring); // Convert the numeric part to an integer
                int newIdIndex = i + 1; // Increment the number by 1
                return String.format("T%03d", newIdIndex); // Return the new customer ID in format Cnnn
            } catch (NumberFormatException e) {
                // Handle cases where the numeric part is invalid
                throw new SQLException("Invalid customer ID format in the database: " + lastTherpist);
            } finally {
                session.close();
            }
        }
        return "T001";
    }

    @Override
    public ArrayList<Therapist> loadTherapistTable(Session session) {
        String hql = "FROM Therapist";
        Query query = session.createQuery(hql);
        List<Therapist> therapists = query.getResultList();
        session.close();
        return new ArrayList<>(therapists);
    }
}
