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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.project.healthCareCenter.bo.SessionBookingBO;
import lk.project.healthCareCenter.bo.impl.SessionBookingBOImpl;
import lk.project.healthCareCenter.controller.SessionBookingController;
import lk.project.healthCareCenter.dto.TherapySessionDTO;
import lk.project.healthCareCenter.entity.TherapySession;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowAllBookingsController implements Initializable {
    @FXML
    private TableView<TherapySessionDTO> bookingsTable;
    @FXML
    private TableColumn<TherapySessionDTO, String> sessionID;
    @FXML
    private TableColumn<TherapySessionDTO, String> sessionDate;
    @FXML
    private TableColumn<TherapySessionDTO, String> sessionTime;
    @FXML
    private TableColumn<TherapySessionDTO, String> patientID;
    @FXML
    private TableColumn<TherapySessionDTO, String> therapistID;

    TherapySessionDTO selectedItem = null;

    private SessionBookingController sessionBookingController;
    private SessionBookingBO sessionBookingBO = new SessionBookingBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sessionID.setCellValueFactory(new PropertyValueFactory<>("sessionID"));
        sessionDate.setCellValueFactory(new PropertyValueFactory<>("sessionDate"));
        sessionTime.setCellValueFactory(new PropertyValueFactory<>("sessionTime"));
        patientID.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getPatientID())
        );
        therapistID.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTherapistID())
        );


    }

    public void selectOnClick(ActionEvent actionEvent) {
        sessionBookingController.getSelectedItem(selectedItem);
        if (selectedItem != null) {
            // Retrieve the stage from the event source (e.g., the button)
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            // Close the stage
            stage.close();
        }
    }

    public void loadTable() {
        ArrayList<TherapySessionDTO> list = sessionBookingBO.showAllBookingsTable();
        ObservableList<TherapySessionDTO> data = FXCollections.observableArrayList(list);
        bookingsTable.getItems().clear();
        bookingsTable.setItems(data);
    }
    //Controller Setter

    public void setSessionBookingController(SessionBookingController sessionBookingController) {
        this.sessionBookingController = sessionBookingController;
    }

    public void tableClickedOnAction(MouseEvent mouseEvent) {
       selectedItem  = bookingsTable.getSelectionModel().getSelectedItem();
    }
}
