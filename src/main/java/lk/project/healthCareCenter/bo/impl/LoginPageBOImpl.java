package lk.project.healthCareCenter.bo.impl;

import lk.project.healthCareCenter.bo.LoginPageBO;
import lk.project.healthCareCenter.dao.LoginPageDAO;
import lk.project.healthCareCenter.dao.impl.LoginPageDAOImpl;
import lk.project.healthCareCenter.entity.User;
import lk.project.healthCareCenter.hibernateConfig.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginPageBOImpl implements LoginPageBO {

    private final LoginPageDAO loginPageDAO = new LoginPageDAOImpl();

    @Override
    public boolean loginCheck(User user) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        return loginPageDAO.loginCheck(user, session);
    }
}
