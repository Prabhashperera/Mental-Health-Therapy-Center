package lk.project.healthCareCenter.dao.impl;

import lk.project.healthCareCenter.dao.SessionBookingDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;

public class SessionBookingDAOImpl implements SessionBookingDAO {
    @Override
    public String generateNextSessionID(Session session) throws SQLException {
        String hql = "SELECT f.sessionID FROM TherapySession f ORDER BY f.sessionID DESC";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        String lastSessionID = (String) query.uniqueResult();
        if (lastSessionID != null && lastSessionID.length() > 1) {
            // Extract the numeric part, make sure there is at least one character after 'C'
            String substring = lastSessionID.substring(1); // Extract the numeric part (after 'C')
            try {
                int i = Integer.parseInt(substring); // Convert the numeric part to an integer
                int newIdIndex = i + 1; // Increment the number by 1
                return String.format("S%03d", newIdIndex); // Return the new customer ID in format Cnnn
            } catch (NumberFormatException e) {
                // Handle cases where the numeric part is invalid
                throw new SQLException("Invalid Session ID format in the database: " + lastSessionID);
            } finally {
                session.close();
            }
        }
        return "S001";
    }
}
