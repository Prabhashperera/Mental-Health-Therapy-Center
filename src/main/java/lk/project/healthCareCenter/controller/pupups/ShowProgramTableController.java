package lk.project.healthCareCenter.controller.pupups;

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
import lk.project.healthCareCenter.bo.RegisterPageBO;
import lk.project.healthCareCenter.bo.impl.RegisterPageBOImpl;
import lk.project.healthCareCenter.controller.SelectProgramPageController;
import lk.project.healthCareCenter.controller.TherapistManageController;
import lk.project.healthCareCenter.entity.TherapyProgram;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowProgramTableController implements Initializable {
    private SelectProgramPageController selectProgramPageController;
    private TherapistManageController therapistManageController;

    @FXML
    private TableColumn<TherapyProgram, String> programID;
    @FXML
    private TableColumn<TherapyProgram, String> programName;
    @FXML
    private TableColumn<TherapyProgram, String> duration;
    @FXML
    private TableColumn<TherapyProgram, String> fee;
    @FXML
    private TableView<TherapyProgram> programTable;

    private RegisterPageBO registerPageBO = new RegisterPageBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        programID.setCellValueFactory(new PropertyValueFactory<>("programID"));
        programName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        duration.setCellValueFactory(new PropertyValueFactory<>("programDuration"));
        fee.setCellValueFactory(new PropertyValueFactory<>("programFee"));

        try {
            loadProgramTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadProgramTable() throws SQLException {
        ArrayList<TherapyProgram> programs = registerPageBO.loadProgramTable();
        ObservableList<TherapyProgram> observableList = FXCollections.observableArrayList(programs);
        programTable.setItems(observableList);
    }

    @FXML
    private void selectOnClick(ActionEvent actionEvent) {
        TherapyProgram selectedItem = programTable.getSelectionModel().getSelectedItem();
        if (therapistManageController != null) {
            therapistManageController.setSelectedProgram(selectedItem);
            //Closing The Stage
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
            return;
        }
        selectProgramPageController.setProgramID(selectedItem.getProgramID());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setSelectProgramController(SelectProgramPageController selectProgramPageController) {
        this.selectProgramPageController = selectProgramPageController;
    }

    public void setTherapistMangeController(TherapistManageController therapistManageController) {
        this.therapistManageController = therapistManageController;
    }

}
