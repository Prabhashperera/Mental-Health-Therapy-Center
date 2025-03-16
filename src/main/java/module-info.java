module org.example.mentalheaththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires jakarta.persistence;
    requires static lombok;


    opens lk.project.helathCareCenter to javafx.fxml;
    exports lk.project.helathCareCenter;
    exports lk.project.helathCareCenter.controller to javafx.fxml;
    opens lk.project.helathCareCenter.controller to javafx.fxml;
}