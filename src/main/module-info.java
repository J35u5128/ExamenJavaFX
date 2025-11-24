module org.example.plantillabdlessfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;


    opens org.example.examenjavafx to javafx.fxml;
    exports org.example.examenjavafx;
    opens org.example.examenjavafx.controllers to javafx.fxml;
    opens org.example.examenjavafx.model to javafx.base;
}