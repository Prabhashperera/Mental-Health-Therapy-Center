package lk.project.healthCareCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lk.project.healthCareCenter.bo.RegisterPageBO;
import lk.project.healthCareCenter.bo.impl.RegisterPageBOImpl;
import lk.project.healthCareCenter.entity.Patient;

public class RegisterPageController {
    @FXML
    private TextField ageLabel;
    @FXML
    private Label idLabel;
    @FXML
    private TextField nameLabel;
    @FXML
    private TextArea noteLabel;
    @FXML
    private TextField numberLabel;

    RegisterPageBO registerPageBO = new RegisterPageBOImpl();

    @FXML
    void saveOnClick(ActionEvent event) {
        String id = idLabel.getText();
        String name = nameLabel.getText();
        String age = String.valueOf(ageLabel.getText());
        String number = numberLabel.getText();
        String note = noteLabel.getText();

        Patient patient = new Patient(id, name, age, number, note);
        boolean isSaved = registerPageBO.savePatient(patient);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Patient registered successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Error").show();
        }
    }

}
