package lk.project.healthCareCenter.dao.impl;

import lk.project.healthCareCenter.dao.SelectProgramDAO;
import lk.project.healthCareCenter.entity.Patient;
import lk.project.healthCareCenter.entity.ProgramDetails;
import lk.project.healthCareCenter.entity.ProgramDetailsId;
import lk.project.healthCareCenter.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SelectProgramDAOImpl implements SelectProgramDAO {
    @Override
    public boolean saveProgramDetails(String patientID, String programID, Session session) {
        try {
            ProgramDetailsId programDetailsId = new ProgramDetailsId(patientID, programID);
            ProgramDetails programDetails = new ProgramDetails();

            TherapyProgram therapyProgram = session.get(TherapyProgram.class, programID);
            Patient patient = session.get(Patient.class, patientID);
            List<ProgramDetails> programDetailsList = new ArrayList<>();

            programDetails.setId(programDetailsId); //Composite Primary Key
            programDetails.setPatient(patient);
            programDetails.setTherapyProgram(therapyProgram);
            //Inserting Program List (Object) to Arraylist
            programDetailsList.add(programDetails);

            //Setting Program Details List to Patient
            patient.setProgramDetails(programDetailsList);
            //Setting Program Details List to Therapy Program Table
            therapyProgram.setProgramDetails(programDetailsList);

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
            // Step 1: Find the existing ProgramDetails record
            ProgramDetailsId oldId = new ProgramDetailsId(clickedPatientID,clickedProgramID);
            ProgramDetails oldDetails = session.find(ProgramDetails.class, oldId);

            if (oldDetails == null) {
                System.out.println("No ProgramDetails found with the provided ProgramDetailsId.");
                return false;
            }

            // Step 2: Fetch the new TherapyProgram entity
            TherapyProgram newTherapyProgram = session.get(TherapyProgram.class, programID);
            if (newTherapyProgram == null) {
                System.out.println("No TherapyProgram found with the provided newProgramID.");
                return false;
            }

            // Step 3: Delete the old record
            session.remove(oldDetails);

            // Step 4: Create new ProgramDetails with updated programID
            ProgramDetailsId newId = new ProgramDetailsId(clickedPatientID, programID);
            ProgramDetails newDetails = new ProgramDetails(newId, oldDetails.getPatient(), newTherapyProgram);

            // Step 5: Save the new entry
            session.persist(newDetails);

            System.out.println("Updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();  // Print error stack
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteProgramDetail(String patient, String program, Session session) {
        try {
            ProgramDetailsId compositeKey = new ProgramDetailsId(patient,program);
            ProgramDetails details = session.get(ProgramDetails.class, compositeKey);
            if (details != null) {
                session.remove(details);
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


//    @Override
//    public boolean updateProgram(String patientID, String newProgramID, String clickedPatientID, String clickedProgramID, Session session) {
//        try {
//            // Create ProgramDetailsId object
////            Patient patient = session.get(Patient.class, clickedPatientID);
////            TherapyProgram program = session.get(TherapyProgram.class, newProgramID);
//
//            ProgramDetailsId programDetailsId = new ProgramDetailsId(clickedPatientID , clickedProgramID);
//            System.out.println("Patient ID: " + patientID + "Program ID: " + newProgramID);
//
//            // Fetch ProgramDetails entity based on the composite key
//            System.out.println("Searching for ProgramDetails with patientID=" + programDetailsId.getPatientID() + " and newProgramID=" + programDetailsId.getProgramID());
//            ProgramDetails details = session.find(ProgramDetails.class, programDetailsId);
//
//            if (details == null) {
//                System.out.println("No ProgramDetails found with the provided ProgramDetailsId.");
//                return false;
//            }
//
//            // Fetch the TherapyProgram entity using the new ProgramID
//            TherapyProgram therapyProgram = session.get(TherapyProgram.class, newProgramID);
//
//            // Set the new TherapyProgram for the ProgramDetails
//            details.setTherapyProgram(therapyProgram);
//
//            // Merge the changes (save or update the entity)
//            session.merge(details);
//            if (!session.contains(details)) {
//                System.out.println("Entity is not managed. Merging...");
//            }
//            session.flush();  // ✅ Force Hibernate to execute SQL update
//        }catch(Exception e){
//            e.getMessage();
//            return false;
//        }
//        return true;
//    }
}
