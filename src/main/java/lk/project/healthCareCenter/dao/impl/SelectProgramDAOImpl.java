package lk.project.healthCareCenter.dao.impl;

import lk.project.healthCareCenter.dao.SelectProgramDAO;
import lk.project.healthCareCenter.entity.Patient;
import lk.project.healthCareCenter.entity.ProgramDetails;
import lk.project.healthCareCenter.entity.ProgramDetailsId;
import lk.project.healthCareCenter.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SelectProgramDAOImpl implements SelectProgramDAO {
    @Override
    public boolean saveProgramDetails(String patientID, String programID, Session session) {
        try {
            ProgramDetailsId programDetailsId = new ProgramDetailsId(patientID, programID);
            Patient patient = session.get(Patient.class, patientID);
            TherapyProgram therapyProgram = session.get(TherapyProgram.class, programID);
            ProgramDetails programDetails = new ProgramDetails();

            programDetails.setId(programDetailsId); //Composite Primary Key

            programDetails.setPatient(patient);
            programDetails.setTherapyProgram(therapyProgram);
            session.persist(programDetails);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Object[]> loadProgramDetails(Session session) {
        String hql = "SELECT p.patientID, p.patientName, tp.programID, tp.programName\n" +
                "FROM Patient p\n" +
                "JOIN p.programDetails pd\n" +
                "JOIN pd.therapyProgram tp\n";

        // Create the query using the HQL string
        Query<Object[]> query = session.createQuery(hql);

        // Execute the query and retrieve the results
        List<Object[]> resultList = query.list();
        return resultList;
    }

    @Override
    public boolean updateProgram(String patientID, String programID, String clickedPatientID, String clickedProgramID, Session session) {
        try {
            // Create ProgramDetailsId object
            ProgramDetailsId programDetailsId = new ProgramDetailsId(clickedPatientID, clickedProgramID);

            // Fetch ProgramDetails entity based on the composite key
            ProgramDetails details = session.get(ProgramDetails.class, programDetailsId);

            if (details == null) {
                // If the details are not found, log the error and return false
                System.out.println("No ProgramDetails found with the provided ProgramDetailsId.");
                return false;
            }

            // Fetch the TherapyProgram entity using the clickedProgramID
            TherapyProgram therapyProgram = session.get(TherapyProgram.class, clickedProgramID);

            if (therapyProgram == null) {
                // If no therapy program is found, return false
                System.out.println("No TherapyProgram found with the provided clickedProgramID.");
                return false;
            }

            // Set the new TherapyProgram for the ProgramDetails
            details.setTherapyProgram(therapyProgram);

            // Merge the changes (save or update the entity)
            session.merge(details);
        }catch(Exception e){
            e.getMessage();
            return false;
        }
        return true;
    }
}
