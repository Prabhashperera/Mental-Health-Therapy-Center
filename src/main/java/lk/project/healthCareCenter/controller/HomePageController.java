package lk.project.healthCareCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class HomePageController {
    public AnchorPane middlePane;

    public void registerOnClick(ActionEvent actionEvent) throws IOException {
        middlePane.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/subPages/registerPage.fxml")));
        middlePane.getChildren().add(pane);
    }

    public void programOnClick(ActionEvent actionEvent) throws IOException {
        middlePane.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/subPages/prgramSelectPage.fxml")));
        middlePane.getChildren().add(pane);
    }

    public void paymentOnClick(ActionEvent actionEvent) {
    }
}
