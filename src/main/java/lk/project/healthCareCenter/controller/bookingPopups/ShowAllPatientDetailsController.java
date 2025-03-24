package lk.project.healthCareCenter.controller.bookingPopups;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.project.healthCareCenter.bo.SessionBookingBO;
import lk.project.healthCareCenter.bo.impl.SessionBookingBOImpl;
import lk.project.healthCareCenter.controller.SessionBookingController;
import lk.project.healthCareCenter.dto.ProgramDetailsDTO;
import lk.project.healthCareCenter.entity.Patient;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowAllPatientDetailsController implements Initializable {
    @FXML
    private TableView<ProgramDetailsDTO> patientTable;
    @FXML
    private TableColumn<ProgramDetailsDTO, String> patientID;
    @FXML
    private TableColumn<ProgramDetailsDTO, String> patientName;
    @FXML
    private TableColumn<ProgramDetailsDTO, String> programID;
    @FXML
    private TableColumn<ProgramDetailsDTO, String> programName;

    private SessionBookingController sessionBookingController;
    private final SessionBookingBO sessionBookingBO = new SessionBookingBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       patientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
       patientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
       programID.setCellValueFactory(new PropertyValueFactory<>("programID"));
        programName.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getProgramName())
        );

       loadTable();

    }

    @FXML
    private void selectOnClick(ActionEvent actionEvent) {
    }

    //Controller Setter
    public void setSessionBookingController(SessionBookingController sessionBookingController) {
        this.sessionBookingController = sessionBookingController;
    }

    //Loader Methods
    public void loadTable() {
        ArrayList<ProgramDetailsDTO> programDetailsList = sessionBookingBO.loadPatientTable();

        // ✅ Convert to ObservableList
        ObservableList<ProgramDetailsDTO> observableList = FXCollections.observableList(programDetailsList);

        // ✅ Set it in the TableView
        patientTable.setItems(observableList);
    }
}
