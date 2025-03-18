package lk.project.healthCareCenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.project.healthCareCenter.bo.RegisterPageBO;
import lk.project.healthCareCenter.bo.impl.RegisterPageBOImpl;
import lk.project.healthCareCenter.entity.Patient;
import lk.project.healthCareCenter.entity.TherapyProgram;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterPageController implements Initializable {
    @FXML
    private Button therapyProgramButton;
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

    private RegisterPageBO registerPageBO = new RegisterPageBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            refreshPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void saveOnClick(ActionEvent event) throws SQLException {
        String id = idLabel.getText();
        String name = nameLabel.getText();
        String age = String.valueOf(ageLabel.getText());
        String number = numberLabel.getText();
        String note = noteLabel.getText();

        //Validation Part Start
        if (id != null && name != null && age != null && number != null && note != null) {
            if (!isValidName(name)) {
                System.out.println("Invalid name! Please enter a valid name.");
                new Alert(Alert.AlertType.ERROR, "Invalid name! Please enter a valid name.").show();
                return;
            } else if (!isValidAge(age)) {
                System.out.println("Invalid age! Please enter a number between 0 and 120.");
                new Alert(Alert.AlertType.ERROR, "Invalid age! Please enter a valid number.").show();
                return;
            } else if (!isValidSriLankanPhoneNumber(number)) {
                System.out.println("Invalid phone number! Please enter a valid number.");
                new Alert(Alert.AlertType.ERROR, "Invalid phone number.").show();
                return;
            }else {
                System.out.println("All inputs are valid! ✅");
            }
        }
        //Validation Part End

        Patient patient = new Patient(id, name, age, number, note);
        boolean isSaved = registerPageBO.savePatient(patient);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Patient registered successfully").show();
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR, "Error, Try Again!!").show();
            refreshPage();
        }
    }
    @FXML
    private void updateOnClick(ActionEvent actionEvent) throws SQLException {
        String id = idLabel.getText();
        String name = nameLabel.getText();
        String age = String.valueOf(ageLabel.getText());
        String number = numberLabel.getText();
        String note = noteLabel.getText();

        //Validation Part Start
        if (id != null && name != null && age != null && number != null && note != null) {
            if (!isValidName(name)) {
                System.out.println("Invalid name! Please enter a valid name.");
                new Alert(Alert.AlertType.ERROR, "Invalid name! Please enter a valid name.").show();
                return;
            } else if (!isValidAge(age)) {
                System.out.println("Invalid age! Please enter a number between 0 and 120.");
                new Alert(Alert.AlertType.ERROR, "Invalid age! Please enter a valid number.").show();
                return;
            } else if (!isValidSriLankanPhoneNumber(number)) {
                System.out.println("Invalid phone number! Please enter a valid number.");
                new Alert(Alert.AlertType.ERROR, "Invalid phone number.").show();
                return;
            }else {
                System.out.println("All inputs are valid! ✅");
            }
        }
        //Validation Part End

        Patient patient = new Patient(id, name, age, number, note);
        if (name != null) {
            boolean isUpdated = registerPageBO.updatePatient(patient);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Patient updated successfully").show();
                refreshPage();
            }else {
                new Alert(Alert.AlertType.ERROR, "Error, Try Again!!").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Select Patient to Update!!").show();
        }
    }
    @FXML
    private void deleteOnClick(ActionEvent actionEvent) throws SQLException {
        String id = idLabel.getText();
        if (id != null && !id.isEmpty()) {
            boolean isDeleted = registerPageBO.deletePatient(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Patient deleted successfully").show();
                refreshPage();
            }else {
                new Alert(Alert.AlertType.ERROR, "Error, Try Again!!").show();
                refreshPage();
            }
        }
    }



    @FXML
    private void selectTherapyOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/popups/showProgramTable.fxml"));
        Parent root = fxmlLoader.load();
        ShowProgramTableController controller = fxmlLoader.getController();
        controller.setRegisterController(this);
        stage.setScene(new Scene(root));
        stage.setTitle("Program Table");
        stage.show();
    }


    //External Methods
    public void refreshPage() throws SQLException {
        idLabel.setText("");
        nameLabel.setText("");
        ageLabel.setText("");
        numberLabel.setText("");
        noteLabel.setText("");
        therapyProgramButton.setText("Select Therapy Program");
        generateNextID();
    }
    public void generateNextID() throws SQLException {
        String generatedID = registerPageBO.generateNextID();
        idLabel.setText(generatedID);
    }

    @FXML
    void showTableOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/popups/showPatientTable.fxml"));
        Parent root = fxmlLoader.load();
        ShowPatientTableController controller = fxmlLoader.getController();
        controller.setRegisterController(this);
        stage.setScene(new Scene(root));
        stage.setTitle("Patient Table");
        stage.show();
    }

    public void setSelectedProgram(TherapyProgram selectedProgram) {
        therapyProgramButton.setText(selectedProgram.getProgramName());
    }
    public void setSelectedPatient(Patient selectedPatient) {
        idLabel.setText(selectedPatient.getPatientID());
        nameLabel.setText(selectedPatient.getPatientName());
        ageLabel.setText(selectedPatient.getPatientAge());
        numberLabel.setText(selectedPatient.getMobileNumber());
        noteLabel.setText(selectedPatient.getMedicalHistory());
    }


    //    Validation Methods
    public static boolean isValidSriLankanPhoneNumber(String phone) {
        String phoneRegex = "^(07[01245678])\\d{7}$"; // Sri Lankan mobile numbers
        return phone.matches(phoneRegex);
    }
    public static boolean isValidAge(String age) {
        try {
            int ageValue = Integer.parseInt(age);
            return ageValue >= 12 && ageValue <= 90; // Assuming a valid human age range
        } catch (NumberFormatException e) {
            return false; // Not a valid number
        }
    }
    public static boolean isValidName(String name) {
        String nameRegex = "^[A-Za-z ]+$"; // Allows only letters and spaces
        return name.matches(nameRegex);
    }
}
