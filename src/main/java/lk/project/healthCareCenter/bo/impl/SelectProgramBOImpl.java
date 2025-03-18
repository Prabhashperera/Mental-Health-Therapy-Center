package lk.project.healthCareCenter.bo.impl;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.project.healthCareCenter.bo.SelectProgramBO;
import lk.project.healthCareCenter.dao.SelectProgramDAO;
import lk.project.healthCareCenter.dao.impl.SelectProgramDAOImpl;
import lk.project.healthCareCenter.hibernateConfig.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SelectProgramBOImpl implements SelectProgramBO {

    private final SelectProgramDAO selectProgramDAO = new SelectProgramDAOImpl();

    @Override
    public boolean saveProgramDetails(String patientID, String programID) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction tx = session.beginTransaction();
        try {
            boolean isSaved = selectProgramDAO.saveProgramDetails(patientID, programID, session);
            if (isSaved) {
                tx.commit();
                return true;
            }
        } catch (Exception e) {
            tx.rollback();
            e.getMessage();
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<Object[]> getProgramDetails() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        return selectProgramDAO.loadProgramDetails(session);
    }

    @Override
    public boolean updateProgramDetail(String patient, String program, String clickedPatientID, String clickedProgramID) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction tx = session.beginTransaction();
        try {
            boolean isUPdated = selectProgramDAO.updateProgram(patient, program, clickedPatientID, clickedProgramID, session);
            if (isUPdated) {
                tx.commit();
                return true;
            }
        }catch (Exception e) {
            tx.rollback();
            e.getMessage();
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
            return false;
        }finally {
            session.close();
        }
        return false;
    }
}
