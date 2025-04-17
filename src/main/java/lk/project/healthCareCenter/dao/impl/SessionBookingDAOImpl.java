package lk.project.healthCareCenter.dao.impl;

import lk.project.healthCareCenter.dao.SessionBookingDAO;
import lk.project.healthCareCenter.dto.CustomProgramDetailsDTO;
import lk.project.healthCareCenter.dto.CustomTherapistDetailsDTO;
import lk.project.healthCareCenter.entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ArrayList<CustomProgramDetailsDTO> loadPatientTable(Session session) {
        String hql = "SELECT p.patientID, p.patientName, tp.programID, tp.programName " +
                "FROM Patient p " +
                "JOIN p.programDetails pd " +
                "JOIN pd.therapyProgram tp";

        // Create the query using the HQL string
        Query<Object[]> query = session.createQuery(hql);

        // Execute the query and retrieve the results
        List<Object[]> resultList = query.list();

        // 🔥 Convert the raw Object[] into ProgramDetailsDTO objects
        ArrayList<CustomProgramDetailsDTO> dtoList = new ArrayList<>();
        for (Object[] row : resultList) {
            dtoList.add(new CustomProgramDetailsDTO(
                    (String) row[0],  // patientID
                    (String) row[1],  // patientName
                    (String) row[2],  // programID
                    (String) row[3]   // programName
            ));
        }

        return dtoList;
    }

    @Override
    public ArrayList<CustomTherapistDetailsDTO> loadTherapistTable(Session session, String patientProgramID, String selectedDateLabel, String selectedTimeLabel) {

        String hql = "SELECT t.therapistID, t.therapistName, tp.programID, tp.programName " +
                "FROM Therapist t " +
                "JOIN t.therapyProgram tp " +
                "WHERE tp.programID = :patientProgramID";


        List<Object[]> resultList = session.createQuery(hql)
                .setParameter("patientProgramID", patientProgramID)
                .getResultList();


        // 🔥 Convert the raw Object[] into ProgramDetailsDTO objects
        ArrayList<CustomTherapistDetailsDTO> dtoList = new ArrayList<>();
        for (Object[] row : resultList) {
            dtoList.add(new CustomTherapistDetailsDTO(
                    (String) row[0],
                    (String) row[1],
                    (String) row[2],
                    (String) row[3]
            ));
        }


        ArrayList<CustomTherapistDetailsDTO> newList = new ArrayList<>();

        for (CustomTherapistDetailsDTO therapist : dtoList) {
            List<TherapySession> sessionsOnDate = session.createQuery(
                            "FROM TherapySession ts WHERE ts.therapist.id = :therapistId AND ts.sessionDate = :selectedDate", TherapySession.class)
                    .setParameter("therapistId", therapist.getTherapistID())
                    .setParameter("selectedDate", selectedDateLabel)
                    .getResultList();

            boolean hasSessionAtExactTime = false;

            for (TherapySession therapySession : sessionsOnDate) {
                if (therapySession.getSessionTime().equals(selectedTimeLabel)) {
                    hasSessionAtExactTime = true;
                    break;
                }
            }

            if (!hasSessionAtExactTime) {
                newList.add(therapist); // Therapist is free at the selected time
            }
        }
        return newList;
    }

    @Override
    public boolean saveSession(TherapySession therapySession, Session session) {
        try {
            session.persist(therapySession);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
