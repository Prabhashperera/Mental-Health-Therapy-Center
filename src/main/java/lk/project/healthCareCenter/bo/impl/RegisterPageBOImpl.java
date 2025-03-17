package lk.project.healthCareCenter.bo.impl;

import lk.project.healthCareCenter.bo.RegisterPageBO;
import lk.project.healthCareCenter.dao.RegisterPageDAO;
import lk.project.healthCareCenter.dao.impl.RegisterPageDAOImpl;
import lk.project.healthCareCenter.entity.Patient;

import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterPageBOImpl implements RegisterPageBO {
    private RegisterPageDAO registerDAO = new RegisterPageDAOImpl();

    @Override
    public boolean savePatient(Patient patient) {
        return registerDAO.savePatient(patient);
    }

    @Override
    public String generateNextID() throws SQLException {
        return registerDAO.generateNextID();
    }

    @Override
    public ArrayList<Patient> loadTable() throws SQLException {
        return registerDAO.loadTable();
    }

    @Override
    public boolean updatePatient(Patient patient) {
        return registerDAO.updatePatient(patient);
    }
}
