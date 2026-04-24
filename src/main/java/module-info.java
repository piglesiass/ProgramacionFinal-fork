module com.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;

    opens com.proyecto to javafx.fxml;
    opens com.proyecto.controllers to javafx.fxml;
    opens com.proyecto.model to com.google.gson;
    exports com.proyecto;
}
