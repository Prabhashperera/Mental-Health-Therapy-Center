package lk.project.healthCareCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.project.healthCareCenter.bo.TherapistManageBO;
import lk.project.healthCareCenter.bo.impl.TherapistManageBOImpl;
import lk.project.healthCareCenter.controller.pupups.ShowPatientTableController;
import lk.project.healthCareCenter.controller.pupups.ShowProgramTableController;
import lk.project.healthCareCenter.entity.Therapist;
import lk.project.healthCareCenter.entity.TherapyProgram;

import java.io.IOException;

public class TherapistManageController {
    @FXML
    private TextField nameLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Button showProgramBtn;

    private final TherapistManageBO therapistManageBO = new TherapistManageBOImpl();
    TherapyProgram therapyProgram = new TherapyProgram();

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
    private void updateOnClick(ActionEvent actionEvent) {
    }

    @FXML
    private void deleteOnClick(ActionEvent actionEvent) {
    }

    @FXML
    private void showTableOnClick(ActionEvent actionEvent) {
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

    public void setSelectedProgram(TherapyProgram program) {
        showProgramBtn.setText(program.getProgramID());
        therapyProgram = program;
    }

}
