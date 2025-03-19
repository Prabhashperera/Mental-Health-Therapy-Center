package lk.project.healthCareCenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.project.healthCareCenter.bo.SelectProgramBO;
import lk.project.healthCareCenter.bo.impl.SelectProgramBOImpl;
import lk.project.healthCareCenter.dto.ProgramDetailsDTO;
import lk.project.healthCareCenter.entity.Patient;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SelectProgramPageController implements Initializable {
    private String oldPatientID;
    private String oldPorgramID;
    @FXML
    private Button saveBtn;
    @FXML
    private TableView<ProgramDetailsDTO> programDetailsTable;
    @FXML
    private TableColumn<ProgramDetailsDTO, String> PatientID;
    @FXML
    private TableColumn<ProgramDetailsDTO, String> ProgramName;
    @FXML
    private TableColumn<ProgramDetailsDTO, String> ProgramID;
    @FXML
    private TableColumn<ProgramDetailsDTO, String> PatientName;


    @FXML
    private Button patientID;
    @FXML
    private Button programID;

    private final SelectProgramBO selectProgramBO = new SelectProgramBOImpl();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PatientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        PatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        ProgramID.setCellValueFactory(new PropertyValueFactory<>("programID"));
        ProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        ProgramID.setCellValueFactory(new PropertyValueFactory<>("programID"));

        loadProgramDetailsTable();
    }

    @FXML
    void saveOnClick(ActionEvent event) {
        String patient = patientID.getText();
        String program = programID.getText();
        if(!patient.equals("Show Patients") && !program.equals("Show Programs")) {
            boolean isSaved = selectProgramBO.saveProgramDetails(patient, program);
            if(isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Program Details Saved", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Program Details Not Saved", ButtonType.OK).show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Please select a valid patient", ButtonType.OK).show();
        }
    }
    @FXML
    void updateOnClick(ActionEvent event) {
        String patient = patientID.getText();
        String program = programID.getText();
        if(!patient.equals("Show Patients") && !program.equals("Show Programs")) {
            boolean isUpdated = selectProgramBO.updateProgramDetail(patient, program, oldPatientID, oldPorgramID);
            if(isUpdated) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Program Details Updated", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Program Details Not Updated", ButtonType.OK).show();
            }
        }
    }
    @FXML
    void deleteOnClick(ActionEvent event) {

    }

    @FXML
    private void showPatientsOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/popups/showPatientTable.fxml"));
        Parent root = fxmlLoader.load();
        ShowPatientTableController controller = fxmlLoader.getController();
        controller.setSelectProgramController(this);
        stage.setScene(new Scene(root));
        stage.setTitle("Patient Table");
        stage.show();
    }

    public void setPatientID(Patient selectedPatient) {
        patientID.setText(selectedPatient.getPatientID());
    }

    public void setProgramID(String selectedProgram) {
        programID.setText(selectedProgram);
    }

    @FXML
    private void showProgramsOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/popups/showProgramTable.fxml"));
        Parent root = fxmlLoader.load();
        ShowProgramTableController controller = fxmlLoader.getController();
        controller.setSelectProgramController(this);
        stage.setScene(new Scene(root));
        stage.setTitle("Program Table");
        stage.show();
    }

    public void loadProgramDetailsTable() {
        List<Object[]> programDetails = selectProgramBO.getProgramDetails();
        ObservableList<ProgramDetailsDTO> programDetailsObservableList = FXCollections.observableArrayList();

        for (Object[] detail : programDetails) {
            String patientID = (String) detail[0];
            String patientName = (String) detail[1];
            String programID = (String) detail[2];
            String programName = (String) detail[3];

            ProgramDetailsDTO dto = new ProgramDetailsDTO(patientID, patientName, programID, programName);
            programDetailsObservableList.add(dto);
        }
        programDetailsTable.setItems(programDetailsObservableList);
    }

    public void refreshPage() {
        loadProgramDetailsTable();
        programID.setText("Show Programs");
        patientID.setText("Show Patients");
        saveBtn.setDisable(false);
    }

    @FXML
    private void onTableClicked(MouseEvent mouseEvent) {
        ProgramDetailsDTO selectedItem = programDetailsTable.getSelectionModel().getSelectedItem();
        //Clicked Item Stored Globally
        oldPatientID = selectedItem.getPatientID();
        oldPorgramID = selectedItem.getProgramID();

        patientID.setText(selectedItem.getPatientID());
        programID.setText(selectedItem.getProgramID());

        saveBtn.setDisable(true);
    }
}
