module org.example.mentalheaththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;


    opens lk.project.helathCareCenter to javafx.fxml;
    exports lk.project.helathCareCenter;
    exports lk.project.helathCareCenter.controller to javafx.fxml;
}