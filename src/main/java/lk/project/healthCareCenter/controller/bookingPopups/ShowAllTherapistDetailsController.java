package lk.project.healthCareCenter.controller.bookingPopups;

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
import lk.project.healthCareCenter.dto.CustomTherapistDetailsDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowAllTherapistDetailsController implements Initializable {
    @FXML
    private TableView<CustomTherapistDetailsDTO> therapistTable;
    @FXML
    private TableColumn<CustomTherapistDetailsDTO, String> therapistID;
    @FXML
    private TableColumn<CustomTherapistDetailsDTO, String> therapistName;
    @FXML
    private TableColumn<CustomTherapistDetailsDTO, String> programID;
    @FXML
    private TableColumn<CustomTherapistDetailsDTO, String> programName;

    private SessionBookingController sessionBookingController;
    private final SessionBookingBO sessionBookingBO = new SessionBookingBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        therapistID.setCellValueFactory(new PropertyValueFactory<>("therapistID"));
        therapistName.setCellValueFactory(new PropertyValueFactory<>("therapistName"));
        programID.setCellValueFactory(new PropertyValueFactory<>("programID"));
        programName.setCellValueFactory(new PropertyValueFactory<>("programName"));

    }

    //Loader Methods
    public void loadTable() {
        String patientProgramID = sessionBookingController.getPatientProgramIDLabel();
        ArrayList<CustomTherapistDetailsDTO> detailsDTOS = sessionBookingBO.loadTherapistTable(patientProgramID,
                sessionBookingController.getSelectedDateLabel(), sessionBookingController.getSelectedTimeLabel()
        );
        ObservableList<CustomTherapistDetailsDTO> data = FXCollections.observableArrayList(detailsDTOS);
        therapistTable.setItems(data);
    }

    //Controller Setter Method
    public void setSessionBookingController(SessionBookingController sessionBookingController) {
        this.sessionBookingController = sessionBookingController;
    }


    public void selectOnClick(ActionEvent actionEvent) {
        CustomTherapistDetailsDTO selectedItem = therapistTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            sessionBookingController.setTherapistBtnDetails(selectedItem);
            // Retrieve the stage from the event source (e.g., the button)
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            // Close the stage
            stage.close();
        }
    }
}
