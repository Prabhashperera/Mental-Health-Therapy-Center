package lk.project.healthCareCenter.controller.bookingPopups;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.project.healthCareCenter.bo.SessionBookingBO;
import lk.project.healthCareCenter.bo.impl.SessionBookingBOImpl;
import lk.project.healthCareCenter.controller.SessionBookingController;
import lk.project.healthCareCenter.dto.CustomProgramDetailsDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowAllPatientDetailsController implements Initializable {
    @FXML
    private TableView<CustomProgramDetailsDTO> patientTable;
    @FXML
    private TableColumn<CustomProgramDetailsDTO, String> patientID;
    @FXML
    private TableColumn<CustomProgramDetailsDTO, String> patientName;
    @FXML
    private TableColumn<CustomProgramDetailsDTO, String> programID;
    @FXML
    private TableColumn<CustomProgramDetailsDTO, String> programName;

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
        CustomProgramDetailsDTO selectedItem = patientTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            sessionBookingController.setPatientBtnAndLabel(selectedItem);
            // Retrieve the stage from the event source (e.g., the button)
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            // Close the stage
            stage.close();
        }
    }

    //Controller Setter
    public void setSessionBookingController(SessionBookingController sessionBookingController) {
        this.sessionBookingController = sessionBookingController;
    }

    //Loader Methods
    public void loadTable() {
        ArrayList<CustomProgramDetailsDTO> programDetailsList = sessionBookingBO.loadPatientTable();

        // ✅ Convert to ObservableList
        ObservableList<CustomProgramDetailsDTO> observableList = FXCollections.observableList(programDetailsList);

        // ✅ Set it in the TableView
        patientTable.setItems(observableList);
    }
}
