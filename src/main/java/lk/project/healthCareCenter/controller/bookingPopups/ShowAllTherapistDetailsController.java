package lk.project.healthCareCenter.controller.bookingPopups;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lk.project.healthCareCenter.controller.SessionBookingController;
import lk.project.healthCareCenter.dto.TherapistDetailsDTO;
import lk.project.healthCareCenter.entity.Therapist;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowAllTherapistDetailsController implements Initializable {
    @FXML
    private TableView<Therapist> therapistTable;
    @FXML
    private TableColumn<TherapistDetailsDTO, String> therapistID;
    @FXML
    private TableColumn<TherapistDetailsDTO, String> therapistName;
    @FXML
    private TableColumn<TherapistDetailsDTO, String> programID;
    @FXML
    private TableColumn<TherapistDetailsDTO, String> programName;

    private SessionBookingController sessionBookingController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    //Controller Setter Method
    public void setSessionBookingController(SessionBookingController sessionBookingController) {
        this.sessionBookingController = sessionBookingController;
    }

    public void selectOnClick(ActionEvent actionEvent) {
    }
}
