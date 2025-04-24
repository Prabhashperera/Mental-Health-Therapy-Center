package lk.project.healthCareCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import lk.project.healthCareCenter.bo.UserManageBO;
import lk.project.healthCareCenter.bo.impl.UserManageBOImpl;
import lk.project.healthCareCenter.dto.UserDTO;
import lk.project.healthCareCenter.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.net.URL;
import java.util.ResourceBundle;

public class UserManageController implements Initializable {

    @FXML
    private Button deleteBtn;

    @FXML
    private MenuButton menuButton;

    @FXML
    private TextField passwordLabel;

    @FXML
    private Button saveBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private TextField userNameLabel;

    private final UserManageBO userManageBO = new UserManageBOImpl();
    private final String userRole = HomePageController.userRole;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!"Admin".equals(userRole)) {
            saveBtn.setDisable(true);
            updateBtn.setDisable(true);
            deleteBtn.setDisable(true);
        }
    }

    @FXML
    void saveOnClick(ActionEvent event) {
        String password = passwordLabel.getText().trim();
        String username = userNameLabel.getText().trim();
        String userRole = menuButton.getText().trim();

        if (username.isEmpty()) {
            System.out.println("Username cannot be empty!");
        } else if (password.isEmpty()) {
            System.out.println("Password cannot be empty!");
        } else if (password.length() < 8) {
            System.out.println("Password must be at least 8 characters long!");
        } else if (!password.matches(".*[A-Z].*")) {
            System.out.println("Password must contain at least one uppercase letter!");
        } else if (!password.matches(".*[a-z].*")) {
            System.out.println("Password must contain at least one lowercase letter!");
        } else if (!password.matches(".*\\d.*")) {
            System.out.println("Password must contain at least one number!");
        } else if (!password.matches(".*[@#$%^&+=!].*")) {
            System.out.println("Password must contain at least one special character (@#$%^&+=!)");
        } else {
            System.out.println("Validation successful! You may proceed, brave user. 🚀");
        }

        //Password Encode by BCrypt
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        password = passwordEncoder.encode(password);

        //User DTO with Encoded Password
        UserDTO dto = new UserDTO(username, password, userRole);
        boolean isSaved = userManageBO.saveUser(dto);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "User successfully saved!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "User could not be saved!").show();
        }

    }
    @FXML
    void deleteOnClick(ActionEvent event) {

    }

    @FXML
    void updateOnClick(ActionEvent event) {
    }


    @FXML
    void showTableOnClick(ActionEvent event) {

    }
    //Menu Btn
    @FXML
    void receptionistOnAction(ActionEvent event) {
        menuButton.setText("Receptionist");
    }
    @FXML
    void adminOnAction(ActionEvent event) {
        menuButton.setText("Admin");
    }
}
