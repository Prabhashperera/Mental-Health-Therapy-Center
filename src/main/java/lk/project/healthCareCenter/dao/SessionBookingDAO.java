package lk.project.healthCareCenter.dao;

import org.hibernate.Session;

import java.sql.SQLException;

public interface SessionBookingDAO {
    String generateNextSessionID(Session session) throws SQLException;
}
