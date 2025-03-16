package lk.project.healthCareCenter.bo.impl;

import lk.project.healthCareCenter.bo.RegisterPageBO;
import lk.project.healthCareCenter.dao.RegisterPageDAO;
import lk.project.healthCareCenter.dao.impl.RegisterPageDAOImpl;
import lk.project.healthCareCenter.entity.Patient;

public class RegisterPageBOImpl implements RegisterPageBO {
    RegisterPageDAO dao = new RegisterPageDAOImpl();

    @Override
    public boolean savePatient(Patient patient) {
        return dao.savePatient(patient);
    }
}
