package lk.project.healthCareCenter.bo;

import lk.project.healthCareCenter.dto.UserDTO;

public interface LoginPageBO {
    boolean loginCheck(UserDTO user);
}
