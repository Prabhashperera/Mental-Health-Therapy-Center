package lk.project.healthCareCenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.project.healthCareCenter.bo.RegisterPageBO;
import lk.project.healthCareCenter.bo.impl.RegisterPageBOImpl;
import lk.project.healthCareCenter.entity.Patient;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowPatientTableController implements Initializable {
    @FXML
    private TableView<Patient> patientTable;
    @FXML
    private TableColumn<Patient, String> id;
    @FXML
    private TableColumn<Patient, String> name;
    @FXML
    private TableColumn<Patient, String> age;
    @FXML
    private TableColumn<Patient, String> note;
    @FXML
    private TableColumn<Patient, String> number;

    RegisterPageController registerPageController;
    RegisterPageBO registerPageBO = new RegisterPageBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        name.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        age.setCellValueFactory(new PropertyValueFactory<>("patientAge"));
        note.setCellValueFactory(new PropertyValueFactory<>("medicalHistory"));
        number.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));

        try {
            loadTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTable() throws SQLException {
        ArrayList<Patient> patients = registerPageBO.loadTable();
        ObservableList<Patient> observableList = FXCollections.observableArrayList(patients);
        patientTable.setItems(observableList);
    }

    public boolean selectPatient() {
        Patient selectedPatient = patientTable.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            registerPageController.setSelectedPatient(selectedPatient); //Throw Selected Patient to Register Controller
            return true;
        }else {
            new Alert(Alert.AlertType.ERROR, "No selected patient", ButtonType.OK).show();
            return false;
        }
    }

    //Initialize Register Controller Class
    public void setRegisterController(RegisterPageController registerPageController) {
        this.registerPageController = registerPageController;
    }

    public void selectOnClick(ActionEvent actionEvent) {
        boolean isSelected = selectPatient();
        if (isSelected) {
            // Retrieve the stage from the event source (e.g., the button)
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            // Close the stage
            stage.close();
        }
    }
}
