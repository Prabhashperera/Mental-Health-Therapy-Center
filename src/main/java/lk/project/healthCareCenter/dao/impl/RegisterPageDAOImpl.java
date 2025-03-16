package lk.project.healthCareCenter.dao.impl;

import lk.project.healthCareCenter.dao.RegisterPageDAO;
import lk.project.healthCareCenter.entity.Patient;
import lk.project.healthCareCenter.hibernateConfig.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RegisterPageDAOImpl implements RegisterPageDAO {
    Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
    Transaction tx = session.beginTransaction();

    @Override
    public boolean savePatient(Patient patient) {
        try {
            session.persist(patient);
            tx.commit();
            System.out.println("Save patient successful");
        }catch(Exception e) {
            tx.rollback();
            System.out.println(e.getMessage());
            System.out.println("awl");
            return false;
        }
        return true;
    }
}
