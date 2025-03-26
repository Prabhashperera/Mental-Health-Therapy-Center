package lk.project.healthCareCenter.controller.bookingPopups;

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
import lk.project.healthCareCenter.dto.TherapistDetailsDTO;
import lk.project.healthCareCenter.entity.Therapist;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowAllTherapistDetailsController implements Initializable {
    @FXML
    private TableView<TherapistDetailsDTO> therapistTable;
    @FXML
    private TableColumn<TherapistDetailsDTO, String> therapistID;
    @FXML
    private TableColumn<TherapistDetailsDTO, String> therapistName;
    @FXML
    private TableColumn<TherapistDetailsDTO, String> programID;
    @FXML
    private TableColumn<TherapistDetailsDTO, String> programName;

    private SessionBookingController sessionBookingController;
    private final SessionBookingBO sessionBookingBO = new SessionBookingBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        therapistID.setCellValueFactory(new PropertyValueFactory<>("therapistID"));
        therapistName.setCellValueFactory(new PropertyValueFactory<>("therapistName"));
        programID.setCellValueFactory(new PropertyValueFactory<>("programID"));
        programName.setCellValueFactory(new PropertyValueFactory<>("programName"));

        loadTable();
    }

    //Loader Methods
    private void loadTable() {
        ArrayList<TherapistDetailsDTO> detailsDTOS = sessionBookingBO.loadTherapistTable();
        ObservableList<TherapistDetailsDTO> data = FXCollections.observableArrayList(detailsDTOS);
        therapistTable.setItems(data);
    }

    //Controller Setter Method
    public void setSessionBookingController(SessionBookingController sessionBookingController) {
        this.sessionBookingController = sessionBookingController;
    }


    public void selectOnClick(ActionEvent actionEvent) {
    }
}
