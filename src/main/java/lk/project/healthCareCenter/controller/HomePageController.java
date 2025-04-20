package lk.project.healthCareCenter.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    public Label currentDateShow;
    public Label currentTimeShow;
    private LoginPageController loginPageController;
    public static String userRole = null;
    @FXML
    private AnchorPane middlePane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy / MM / dd");
        String currentDate = dateFormat.format(new Date());
        currentDateShow.setText(currentDate);
        currentTimeShowThread();
    }

    //Time & Date Methods
    public void currentTimeShowThread() {
        Thread thread = new Thread(() -> {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss"); // 24-hour format
            while (true) {
                String currentTime = timeFormat.format(new Date());
                Platform.runLater(() -> {
                    currentTimeShow.setText(currentTime);
                });
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true); // Let the app close gracefully
        thread.start();
    }
    public String getCurrentDate() {
        return currentDateShow.getText();
    }
    public String getCurrentTime() {
        return currentTimeShow.getText();
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
