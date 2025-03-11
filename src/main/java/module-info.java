module org.example.mentalheaththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;


    opens lk.project.helathCareCenter to javafx.fxml;
    exports lk.project.helathCareCenter;
    exports lk.project.helathCareCenter.controller to javafx.fxml;
}