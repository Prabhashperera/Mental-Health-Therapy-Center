module org.example.mentalheaththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires jakarta.persistence;
    requires static lombok;


    opens lk.project.healthCareCenter to javafx.fxml;
    exports lk.project.healthCareCenter;
    exports lk.project.healthCareCenter.controller to javafx.fxml;
    opens lk.project.healthCareCenter.controller to javafx.fxml;
    opens lk.project.healthCareCenter.entity to org.hibernate.orm.core, javafx.base;
    opens lk.project.healthCareCenter.dto to org.hibernate.orm.core, javafx.base;
    exports lk.project.healthCareCenter.controller.pupups to javafx.fxml;
    opens lk.project.healthCareCenter.controller.pupups to javafx.fxml;
    opens lk.project.healthCareCenter.controller.bookingPopups to javafx.fxml;

}