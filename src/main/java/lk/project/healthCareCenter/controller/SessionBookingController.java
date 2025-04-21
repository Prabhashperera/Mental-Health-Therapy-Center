package lk.project.healthCareCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.project.healthCareCenter.bo.PaymentBO;
import lk.project.healthCareCenter.bo.SessionBookingBO;
import lk.project.healthCareCenter.bo.impl.PaymentBOImpl;
import lk.project.healthCareCenter.bo.impl.SessionBookingBOImpl;
import lk.project.healthCareCenter.controller.bookingPopups.ShowAllBookingsController;
import lk.project.healthCareCenter.controller.bookingPopups.ShowAllPatientDetailsController;
import lk.project.healthCareCenter.controller.bookingPopups.ShowAllTherapistDetailsController;
import lk.project.healthCareCenter.dto.CustomProgramDetailsDTO;
import lk.project.healthCareCenter.dto.CustomTherapistDetailsDTO;
import lk.project.healthCareCenter.dto.TherapySessionDTO;
import lk.project.healthCareCenter.entity.TherapySession;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SessionBookingController implements Initializable {
    @FXML
    private Label availableWeeksLabel;
    @FXML
    private Label toPayAmountLabel;
    @FXML
    private Text therapistNameLabel;
    @FXML
    private Button patientIDBtn;
    @FXML
    private Button TherapistIDLabel;
    @FXML
    private Text patientProgramIDLabel;
    @FXML
    private DatePicker dateLabel;
//    Menu Button
    @FXML
    private MenuButton timeMenuBtn;
    @FXML
    private MenuItem oneThirty;
    @FXML
    private MenuItem twoThirty;
    @FXML
    private MenuItem threeThirty;
    @FXML
    private MenuItem fourThirty;
    @FXML
    private MenuItem fiveThirty;
    @FXML
    private MenuItem sixThirty;
//    Menu Button End

    @FXML
    private Label sessionIDLabel;

    //BO CLASS INJECTION
    private final SessionBookingBO sessionBookingBO = new SessionBookingBOImpl();
    private final PaymentBO paymentBO = new PaymentBOImpl();

    //Controller
    HomePageController homePageController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set onAction for MenuItems
        for (MenuItem item : timeMenuBtn.getItems()) {
            item.setOnAction(this::menuBtnOnClick);
        }
        checkRequiredHeirarchy();
        refreshPage();
    }


    //POPUPS METHODS
    @FXML
    private void showPatientTableOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/bookingPagePopups/showAllPatientDetails.fxml"));
        Parent root = fxmlLoader.load();
        ShowAllPatientDetailsController controller = fxmlLoader.getController();
        controller.setSessionBookingController(this);
        stage.setScene(new Scene(root));
        stage.setTitle("Patient Table");
        stage.show();
    }

    @FXML
    private void showTherapistTableOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/bookingPagePopups/showAllTherapistDetails.fxml"));
        Parent root = fxmlLoader.load();
        ShowAllTherapistDetailsController controller = fxmlLoader.getController();
        controller.setSessionBookingController(this);
        controller.loadTable();
        stage.setScene(new Scene(root));
        stage.setTitle("Therapist Table");
        stage.show();
    }

    @FXML
    private void showBookingsTableOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/bookingPagePopups/showAllBookings.fxml"));
        Parent root = fxmlLoader.load();
        ShowAllBookingsController controller = fxmlLoader.getController();
        controller.setSessionBookingController(this);
        controller.loadTable();
        stage.setScene(new Scene(root));
        stage.setTitle("Bookings Table");
        stage.show();
    }


    //CRUD METHODS
    @FXML
    private void saveOnClick(ActionEvent actionEvent) {
        String sessionID = sessionIDLabel.getText();
        String date = dateLabel.getValue().toString();
        String time = timeMenuBtn.getText();
        String therapistID = TherapistIDLabel.getText();
        String patientID = patientIDBtn.getText();
        String toPayAmount = toPayAmountLabel.getText();
        TherapySessionDTO sessionDTO = new TherapySessionDTO(sessionID, date, time, therapistID, patientID);
        boolean isSaved = sessionBookingBO.saveSession(sessionDTO);
        if (isSaved) {
            boolean isSavedPayment = paymentBO.savePayment(date, toPayAmount, patientID, patientProgramIDLabel.getText());
            if (isSavedPayment) {
                new Alert(Alert.AlertType.INFORMATION, "Saved Payment").show();
            }
        }

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Session Saved", ButtonType.OK).show();
            refreshPage();
        }else  {
            new Alert(Alert.AlertType.ERROR, "Could not save session", ButtonType.OK).show();
            refreshPage();
        }
    }

    @FXML
    private void updateOnClick(ActionEvent actionEvent) {
        String sessionID = sessionIDLabel.getText();
        String date = dateLabel.getValue().toString();
        String time = timeMenuBtn.getText();
        String therapistID = TherapistIDLabel.getText();
        String patientID = patientIDBtn.getText();
        TherapySessionDTO sessionDTO = new TherapySessionDTO(sessionID, date, time, therapistID, patientID);
        boolean isUpdated = sessionBookingBO.updateBooking(sessionDTO);
        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Session Updated", ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Could not update session", ButtonType.OK).show();
        }
        refreshPage();
    }

    @FXML
    private void deleteOnClick(ActionEvent actionEvent) {
        String sessionID = sessionIDLabel.getText();
        boolean isDeleted = sessionBookingBO.deleteBooking(sessionID);
        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION, "Session Deleted", ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Could not delete session", ButtonType.OK).show();
        }
        refreshPage();
    }


    //Helper Methods
    public void refreshPage() {
        sessionIDLabel.setText(getNextSessionID());
        dateLabel.setValue(null);
        timeMenuBtn.setText("Select Time");
        checkRequiredHeirarchy();
    }

    public void checkRequiredHeirarchy() {
        if (timeMenuBtn.getText().equals("Select Booking Time")) {
            patientIDBtn.setDisable(true);
            TherapistIDLabel.setDisable(true);
        }else if (!patientIDBtn.getText().equals("Select Booking Time")) {
            patientIDBtn.setDisable(false);
            if (patientIDBtn.getText().equals("Select")) {
                TherapistIDLabel.setDisable(true);
            }else {
                TherapistIDLabel.setDisable(false);
            }
        }else {
            patientIDBtn.setDisable(false);
            TherapistIDLabel.setDisable(false);
        }
        System.out.println("called");
    }

    public String getNextSessionID() {
        return sessionBookingBO.generateNextSessionID();
    }

    //Field Setter Methods
    public void setPatientBtnAndLabel(CustomProgramDetailsDTO selectedItem) {
        patientIDBtn.setText(selectedItem.getPatientID());
        patientProgramIDLabel.setText(selectedItem.getProgramID());
        checkRequiredHeirarchy();
        //Set Payement for Program Each Week
        double toPayAmount = paymentBO.checkProgramPayments(patientProgramIDLabel.getText());
        toPayAmountLabel.setText(toPayAmount + "");
        String patientID = patientIDBtn.getText();
        String programID = patientProgramIDLabel.getText();
        String weeks = paymentBO.checkAvailableWeeks(patientID , programID);
        availableWeeksLabel.setText(weeks);
    }

    public void setTherapistBtnDetails(CustomTherapistDetailsDTO selectedItem) {
        TherapistIDLabel.setText(selectedItem.getTherapistID());
        therapistNameLabel.setText(selectedItem.getTherapistName());
    }

    @FXML
    private void menuBtnOnClick(ActionEvent actionEvent) {
        MenuItem menuItem = (MenuItem) actionEvent.getSource();
        timeMenuBtn.setText(menuItem.getText());
        checkRequiredHeirarchy();
    }

    public String getPatientProgramIDLabel() {
        return patientProgramIDLabel.getText();
    }

    public String getSelectedDateLabel() {
        return dateLabel.getValue().toString();
    }
    public String getSelectedTimeLabel() {
        return timeMenuBtn.getText();
    }

    public void getSelectedItem(TherapySessionDTO selectedItem) {
        sessionIDLabel.setText(selectedItem.getSessionID());
        dateLabel.setValue(LocalDate.parse(selectedItem.getSessionDate()));
        timeMenuBtn.setText(selectedItem.getSessionTime());
        patientIDBtn.setText(selectedItem.getPatientID());
        TherapistIDLabel.setText(selectedItem.getTherapistID());
        checkRequiredHeirarchy();
        therapistNameLabel.setText(selectedItem.getTherapistID());
        patientProgramIDLabel.setText(selectedItem.getPatientID());
    }

    public void setHomePageController(HomePageController homePageControllerLink) {
        homePageController = homePageControllerLink;
    }
}