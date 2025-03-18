package lk.project.healthCareCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lk.project.healthCareCenter.entity.Patient;

import java.io.IOException;

public class SelectProgramPageController {
    @FXML
    private Button patientID;
    @FXML
    private Button programID;

    @FXML
    void saveOnClick(ActionEvent event) {

    }
    @FXML
    void updateOnClick(ActionEvent event) {

    }
    @FXML
    void deleteOnClick(ActionEvent event) {

    }

    @FXML
    private void showPatientsOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/popups/showPatientTable.fxml"));
        Parent root = fxmlLoader.load();
        ShowPatientTableController controller = fxmlLoader.getController();
        controller.setSelectProgramController(this);
        stage.setScene(new Scene(root));
        stage.setTitle("Patient Table");
        stage.show();
    }

    public void setPatientID(Patient selectedPatient) {
        patientID.setText(selectedPatient.getPatientID());
    }

    @FXML
    private void showProgramsOnClick(ActionEvent actionEvent) {
    }
}
