package lk.project.healthCareCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    private LoginPageController loginPageController;
    public static String userRole = null;
    @FXML
    private AnchorPane middlePane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void registerOnClick(ActionEvent actionEvent) throws IOException {
        middlePane.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/subPages/registerPage.fxml")));
        middlePane.getChildren().add(pane);
    }

    @FXML
    private void programOnClick(ActionEvent actionEvent) throws IOException {
        middlePane.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/subPages/prgramSelectPage.fxml")));
        middlePane.getChildren().add(pane);
    }

    @FXML
    private void paymentOnClick(ActionEvent actionEvent) {
    }

    @FXML
    private void therapistManageOnClick(ActionEvent actionEvent) throws IOException {
        middlePane.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/subPages/therapistManagePage.fxml")));
        middlePane.getChildren().add(pane);
    }

    public void setLoginPageController(LoginPageController loginPageController) {
        this.loginPageController = loginPageController;
        userRole = loginPageController.getRole();
        System.out.println("Welcome : " + userRole);
    }

    @FXML
    private void sessionBookingOnClick(ActionEvent actionEvent) throws IOException {
        middlePane.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/subPages/sessionBookingPage.fxml")));
        middlePane.getChildren().add(pane);
    }
}
