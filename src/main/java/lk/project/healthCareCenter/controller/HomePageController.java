package lk.project.healthCareCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class HomePageController {
    @FXML
    private AnchorPane middlePane;

    @FXML
    private void registerOnClick(ActionEvent actionEvent) throws IOException {
        middlePane.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/subPages/registerPage.fxml")));
        middlePane.getChildren().add(pane);
    }

    @FXML
    private void programOnClick(ActionEvent actionEvent) throws IOException {
        middlePane.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/subPages/prgramSelectPage.fxml")));
        middlePane.getChildren().add(pane);
    }

    @FXML
    private void paymentOnClick(ActionEvent actionEvent) {
    }

    @FXML
    private void therapistManageOnClick(ActionEvent actionEvent) throws IOException {
        middlePane.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/subPages/therapistManage.fxml")));
        middlePane.getChildren().add(pane);
    }
}
