package lk.project.healthCareCenter.bo;

import lk.project.healthCareCenter.dto.UserDTO;

public interface UserManageBO {
    boolean saveUser(UserDTO user);
}
