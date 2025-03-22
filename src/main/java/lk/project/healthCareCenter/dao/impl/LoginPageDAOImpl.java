package lk.project.healthCareCenter.dao.impl;

import lk.project.healthCareCenter.dao.LoginPageDAO;
import lk.project.healthCareCenter.entity.User;
import org.hibernate.Session;

public class LoginPageDAOImpl implements LoginPageDAO {
    @Override
    public boolean loginCheck(User user, Session session) {
        try {
            User checkIsValidUser = session.get(User.class, user.getUserName());
            if (checkIsValidUser != null) {
                System.out.println(checkIsValidUser.getUserName() + " : " + checkIsValidUser.getUserRole());
                return user.getPassword().equals(checkIsValidUser.getPassword()) && user.getUserRole().equals(checkIsValidUser.getUserRole());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
