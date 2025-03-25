package lk.project.healthCareCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.project.healthCareCenter.bo.SessionBookingBO;
import lk.project.healthCareCenter.bo.impl.SessionBookingBOImpl;
import lk.project.healthCareCenter.controller.bookingPopups.ShowAllPatientDetailsController;
import lk.project.healthCareCenter.dto.ProgramDetailsDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SessionBookingController implements Initializable {
    @FXML
    private Button patientIDBtn;
    @FXML
    private Button TherapistIDLabel;
    @FXML
    private Text patientProgramIDLabel;
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

    //Field Setter Methods
    public void setPatientBtnAndLabel(ProgramDetailsDTO selectedItem) {
        patientIDBtn.setText(selectedItem.getPatientID());
        patientProgramIDLabel.setText(selectedItem.getProgramID());
    }

}
