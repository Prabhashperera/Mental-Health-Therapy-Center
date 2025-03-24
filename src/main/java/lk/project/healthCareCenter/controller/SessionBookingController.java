package lk.project.healthCareCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import lk.project.healthCareCenter.bo.SessionBookingBO;
import lk.project.healthCareCenter.bo.impl.SessionBookingBOImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class SessionBookingController implements Initializable {
    @FXML
    private DatePicker dateLabel;
    @FXML
    private MenuButton timeMenuBtn;
    @FXML
    private Label sessionIDLabel;

    //BO CLASS INJECTION
    private final SessionBookingBO sessionBookingBO = new SessionBookingBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshPage();
    }


    //POPUPS METHODS
    @FXML
    private void showPatientTableOnClick(ActionEvent actionEvent) {
    }

    @FXML
    private void showTherapistTableOnClick(ActionEvent actionEvent) {
    }

    @FXML
    private void showBookingsTableOnClick(ActionEvent actionEvent) {
    }




    //CRUD METHODS

    @FXML
    private void deleteOnClick(ActionEvent actionEvent) {
    }

    @FXML
    private void updateOnClick(ActionEvent actionEvent) {
    }

    @FXML
    private void saveOnClick(ActionEvent actionEvent) {
    }

    //Helper Methods
    public void refreshPage() {
        sessionIDLabel.setText(getNextSessionID());
    }

    public String getNextSessionID() {
        return sessionBookingBO.generateNextSessionID();
    }

}
