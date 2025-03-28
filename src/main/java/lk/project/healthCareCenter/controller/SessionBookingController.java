package lk.project.healthCareCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.project.healthCareCenter.bo.SessionBookingBO;
import lk.project.healthCareCenter.bo.impl.SessionBookingBOImpl;
import lk.project.healthCareCenter.controller.bookingPopups.ShowAllPatientDetailsController;
import lk.project.healthCareCenter.controller.bookingPopups.ShowAllTherapistDetailsController;
import lk.project.healthCareCenter.dto.CustomProgramDetailsDTO;
import lk.project.healthCareCenter.dto.CustomTherapistDetailsDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SessionBookingController implements Initializable {
    @FXML
    private Text therapistNameLabel;
    @FXML
    private Button patientIDBtn;
    @FXML
    private Button TherapistIDLabel;
    @FXML
    private Text patientProgramIDLabel;
    @FXML
    private DatePicker dateLabel;
//    Menu Button
    @FXML
    private MenuButton timeMenuBtn;
    @FXML
    private MenuItem oneThirty;
    @FXML
    private MenuItem twoThirty;
    @FXML
    private MenuItem threeThirty;
    @FXML
    private MenuItem fourThirty;
    @FXML
    private MenuItem fiveThirty;
    @FXML
    private MenuItem sixThirty;
//    Menu Button End

    @FXML
    private Label sessionIDLabel;

    //BO CLASS INJECTION
    private final SessionBookingBO sessionBookingBO = new SessionBookingBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set onAction for MenuItems
        for (MenuItem item : timeMenuBtn.getItems()) {
            item.setOnAction(this::menuBtnOnClick);
        }
        checkRequiredHeirarchy();
        refreshPage();
    }


    //POPUPS METHODS
    @FXML
    private void showPatientTableOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/bookingPagePopups/showAllPatientDetails.fxml"));
        Parent root = fxmlLoader.load();
        ShowAllPatientDetailsController controller = fxmlLoader.getController();
        controller.setSessionBookingController(this);
        stage.setScene(new Scene(root));
        stage.setTitle("Patient Table");
        stage.show();
    }

    @FXML
    private void showTherapistTableOnClick(ActionEvent actionEvent) throws IOException {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/bookingPagePopups/showAllTherapistDetails.fxml"));
        Parent root = fxmlLoader.load();
        ShowAllTherapistDetailsController controller = fxmlLoader.getController();
        controller.setSessionBookingController(this);
        stage.setScene(new Scene(root));
        stage.setTitle("Therapist Table");
        stage.show();
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
        checkRequiredHeirarchy();
    }

    public void checkRequiredHeirarchy() {
        if (timeMenuBtn.getText().equals("Select Booking Time")) {
            patientIDBtn.setDisable(true);
            TherapistIDLabel.setDisable(true);
        }else if (!patientIDBtn.getText().equals("Select Booking Time")) {
            patientIDBtn.setDisable(false);
            if (patientIDBtn.getText().equals("Select")) {
                TherapistIDLabel.setDisable(true);
            }else {
                TherapistIDLabel.setDisable(false);
            }
        }else {
            patientIDBtn.setDisable(false);
            TherapistIDLabel.setDisable(false);
        }
        System.out.println("called");
    }

    public String getNextSessionID() {
        return sessionBookingBO.generateNextSessionID();
    }

    //Field Setter Methods
    public void setPatientBtnAndLabel(CustomProgramDetailsDTO selectedItem) {
        patientIDBtn.setText(selectedItem.getPatientID());
        patientProgramIDLabel.setText(selectedItem.getProgramID());
        checkRequiredHeirarchy();
    }

    public void setTherapistBtnDetails(CustomTherapistDetailsDTO selectedItem) {
        TherapistIDLabel.setText(selectedItem.getTherapistID());
        therapistNameLabel.setText(selectedItem.getTherapistName());
    }

    @FXML
    private void menuBtnOnClick(ActionEvent actionEvent) {
        MenuItem menuItem = (MenuItem) actionEvent.getSource();
        timeMenuBtn.setText(menuItem.getText());
        checkRequiredHeirarchy();
    }
}