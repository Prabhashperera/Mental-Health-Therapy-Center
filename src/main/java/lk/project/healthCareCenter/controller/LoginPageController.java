package lk.project.healthCareCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lk.project.healthCareCenter.bo.LoginPageBO;
import lk.project.healthCareCenter.bo.impl.LoginPageBOImpl;
import lk.project.healthCareCenter.controller.pupups.ShowPatientTableController;
import lk.project.healthCareCenter.entity.User;
import java.io.IOException;

public class LoginPageController {
    boolean isLoggedIn = false;
    public MenuItem adminOption;
    public MenuItem userOption;
    @FXML
    private TextField userNameLabel;
    @FXML
    private TextField passwordLabel;
    @FXML
    private MenuButton menuButton;

    private final LoginPageBO loginPageBO = new LoginPageBOImpl();

    @FXML
    private void loginOnClick(ActionEvent actionEvent) throws IOException {
        String userName = userNameLabel.getText();
        String password = passwordLabel.getText();
        String userRole = menuButton.getText();

        if (userName.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Username is required", ButtonType.OK).show();
            return;
        }
        if (password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Password is required", ButtonType.OK).show();
            return;
        }
        if (userRole.equals("Select Role")) {
            new Alert(Alert.AlertType.ERROR, "Select Your Role", ButtonType.OK).show();
            return;
        }

        User user = new User(userName, password, userRole);
        boolean isValidCredential = loginPageBO.loginCheck(user);
        if (isValidCredential) {
            isLoggedIn = true;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/home-page.fxml"));
            Parent root = fxmlLoader.load();
            HomePageController homePageController = fxmlLoader.getController();

            homePageController.setLoginPageController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Home Page");
            stage.show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Credentials", ButtonType.OK).show();
            menuButton.setText("Select Role");
            passwordLabel.setText("");
            userNameLabel.setText("");
            return;
        }

    }

    public String getRole() {
        if (isLoggedIn) {
            return menuButton.getText();
        }
        return null;
    }

    public void adminOptionOnClick(ActionEvent actionEvent) {
        menuButton.setText("Admin");
    }

    public void userOptionOnClick(ActionEvent actionEvent) {
        menuButton.setText("Receptionist");
    }
}
