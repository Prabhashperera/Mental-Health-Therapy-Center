package lk.project.healthCareCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.project.healthCareCenter.bo.TherapistManageBO;
import lk.project.healthCareCenter.bo.impl.TherapistManageBOImpl;
import lk.project.healthCareCenter.controller.pupups.ShowProgramTableController;
import lk.project.healthCareCenter.controller.pupups.ShowTherapistTableController;
import lk.project.healthCareCenter.entity.Therapist;
import lk.project.healthCareCenter.entity.TherapyProgram;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TherapistManageController implements Initializable {
    private String userRole = HomePageController.userRole;

    @FXML
    private TextField nameLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Button showProgramBtn;
    @FXML
    private Button saveBtn;

    private final TherapistManageBO therapistManageBO = new TherapistManageBOImpl();
    TherapyProgram therapyProgram = new TherapyProgram();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            refreshPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void saveOnClick(ActionEvent actionEvent) {
        String id = idLabel.getText();
        String name = nameLabel.getText();
        TherapyProgram program = therapyProgram;
        Therapist therapist = new Therapist(id, name, program);
        if (id != null || name != null || program != null) {
            boolean isSaved = therapistManageBO.saveTherapist(therapist);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Saved Successfully").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Saving Failed").show();
            }
        }

    }

    @FXML
    private void updateOnClick(ActionEvent actionEvent) throws SQLException {
        String id = idLabel.getText();
        String name = nameLabel.getText();
        Therapist therapist = new Therapist(id, name , therapyProgram);
        boolean isUpdated = therapistManageBO.updateTherapist(therapist);
        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR, "Update Failed").show();
        }
    }

    @FXML
    private void deleteOnClick(ActionEvent actionEvent) {
    }

    @FXML
    private void showTherapistTableOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/popups/showTherapistTable.fxml"));
        Parent root = fxmlLoader.load();
        ShowTherapistTableController controller = fxmlLoader.getController();
        controller.setTherapistManageController(this);
        stage.setScene(new Scene(root));
        stage.setTitle("Therapist Table");
        stage.show();
    }

    @FXML
    private void showProgramOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/popups/showProgramTable.fxml"));
        Parent root = fxmlLoader.load();
        ShowProgramTableController controller = fxmlLoader.getController();
        controller.setTherapistMangeController(this);
        stage.setScene(new Scene(root));
        stage.setTitle("Patient Table");
        stage.show();
    }

    public void refreshPage() throws SQLException {
        generateNextID();
        nameLabel.setText("");
        showProgramBtn.setText("Select Program");
        saveBtn.setDisable(false);
    }

    public void generateNextID() throws SQLException {
        String generatedID = therapistManageBO.generateNextID();
        idLabel.setText(generatedID);
    }

    public void setSelectedProgram(TherapyProgram program) {
        showProgramBtn.setText(program.getProgramID());
        therapyProgram = program;
    }

    public void getSelectedTherapist(Therapist selectedItem) {
        idLabel.setText(selectedItem.getTherapistID());
        nameLabel.setText(selectedItem.getTherapistName());
        showProgramBtn.setText(selectedItem.getTherapyProgram().getProgramID());
        saveBtn.setDisable(true);
    }
}
