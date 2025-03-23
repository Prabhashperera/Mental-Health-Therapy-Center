package lk.project.healthCareCenter.controller.pupups;

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
import lk.project.healthCareCenter.bo.TherapistManageBO;
import lk.project.healthCareCenter.bo.impl.TherapistManageBOImpl;
import lk.project.healthCareCenter.controller.TherapistManageController;
import lk.project.healthCareCenter.entity.Therapist;
import lk.project.healthCareCenter.entity.TherapyProgram;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowTherapistTableController implements Initializable {
    private final TherapistManageBO therapistManageBO = new TherapistManageBOImpl();
    private TherapistManageController therapistManageController;

    @FXML
    private TableView<Therapist> therapistTable;
    @FXML
    private TableColumn<Therapist, String> therapistName;
    @FXML
    private TableColumn<Therapist, String> therapistID;
    @FXML
    private TableColumn<Therapist, String> programID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        therapistID.setCellValueFactory(new PropertyValueFactory<>("therapistID"));
        therapistName.setCellValueFactory(new PropertyValueFactory<>("therapistName"));
        programID.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTherapyProgram().getProgramID())
        );

        loadTable();
    }

    @FXML
    private void selectOnClick(ActionEvent actionEvent) {
        Therapist selectedItem = therapistTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            therapistManageController.getSelectedTherapist(selectedItem);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }else {
            new Alert(Alert.AlertType.ERROR, "Select A Therapist", ButtonType.OK).show();
        }
    }

    public void loadTable() {
        ArrayList<Therapist> therapistData = therapistManageBO.loadTherapistTable();
        ObservableList<Therapist> therapists = FXCollections.observableArrayList(therapistData);
        therapistTable.setItems(therapists);
    }

    public void setTherapistManageController(TherapistManageController controller) {
        this.therapistManageController = controller;
    }
}
