package lk.project.healthCareCenter.bo.impl;

import lk.project.healthCareCenter.bo.LoginPageBO;
import lk.project.healthCareCenter.dao.LoginPageDAO;
import lk.project.healthCareCenter.dao.impl.LoginPageDAOImpl;
import lk.project.healthCareCenter.dto.UserDTO;
import lk.project.healthCareCenter.entity.User;
import lk.project.healthCareCenter.hibernateConfig.FactoryConfiguration;
import org.hibernate.Session;

public class LoginPageBOImpl implements LoginPageBO {

    private final LoginPageDAO loginPageDAO = new LoginPageDAOImpl();

    @Override
    public boolean loginCheck(UserDTO userDTO) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        User user = new User(userDTO.getUserName(), userDTO.getPassword(), userDTO.getUserRole());
        return loginPageDAO.loginCheck(user, session);
    }
}
