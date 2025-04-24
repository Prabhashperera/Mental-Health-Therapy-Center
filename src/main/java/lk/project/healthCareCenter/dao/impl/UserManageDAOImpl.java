package lk.project.healthCareCenter.dao.impl;

import lk.project.healthCareCenter.dao.UserManageDAO;
import lk.project.healthCareCenter.entity.User;
import org.hibernate.Session;

public class UserManageDAOImpl implements UserManageDAO {
    @Override
    public boolean saveUser(Session session, User user) {
        try {
            session.persist(user);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
