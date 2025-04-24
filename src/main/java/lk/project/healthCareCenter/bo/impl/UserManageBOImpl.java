package lk.project.healthCareCenter.bo.impl;

import lk.project.healthCareCenter.bo.UserManageBO;
import lk.project.healthCareCenter.dao.UserManageDAO;
import lk.project.healthCareCenter.dao.impl.UserManageDAOImpl;
import lk.project.healthCareCenter.dto.UserDTO;
import lk.project.healthCareCenter.entity.User;
import lk.project.healthCareCenter.hibernateConfig.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserManageBOImpl implements UserManageBO {
    UserManageDAO userManageDAO = new UserManageDAOImpl();

    @Override
    public boolean saveUser(UserDTO userDTO) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        User user = new User(userDTO.getUserName(), userDTO.getPassword(), userDTO.getUserRole());
        try {
            boolean isSaved = userManageDAO.saveUser(session, user);
            transaction.commit();
            return isSaved;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }
}
