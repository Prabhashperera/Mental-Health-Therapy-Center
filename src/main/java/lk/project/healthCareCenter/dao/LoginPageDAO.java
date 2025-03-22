package lk.project.healthCareCenter.dao;

import lk.project.healthCareCenter.entity.User;
import org.hibernate.Session;

public interface LoginPageDAO {
    boolean loginCheck(User user, Session session);
}
