package lk.project.healthCareCenter.dao;

import lk.project.healthCareCenter.entity.User;
import org.hibernate.Session;

public interface UserManageDAO {
    boolean saveUser(Session session, User user);
}
