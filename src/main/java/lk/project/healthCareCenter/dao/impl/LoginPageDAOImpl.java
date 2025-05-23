package lk.project.healthCareCenter.dao.impl;

import lk.project.healthCareCenter.dao.LoginPageDAO;
import lk.project.healthCareCenter.entity.User;
import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LoginPageDAOImpl implements LoginPageDAO {
    @Override
    public boolean loginCheck(User user, Session session) {
        try {
            // Grab the user from DB using Hibernate session
            User checkIsValidUser = session.get(User.class, user.getUserName());

            if (checkIsValidUser != null) {
                System.out.println(checkIsValidUser.getUserName() + " : " + checkIsValidUser.getUserRole());

                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                boolean passwordMatches = passwordEncoder.matches(user.getPassword(), checkIsValidUser.getPassword());
                boolean roleMatches = user.getUserRole().equals(checkIsValidUser.getUserRole());

                return passwordMatches && roleMatches;
            }

        } catch (Exception e) {
            throw new RuntimeException("Opssssssssssssssssssss", e);
        }

        return false;
    }

}
