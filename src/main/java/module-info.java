module org.example.examenjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.examenjavafx to javafx.fxml;
    exports org.example.examenjavafx;
    opens org.example.examenjavafx.controllers to javafx.fxml;
    opens org.example.examenjavafx.model to javafx.base;
}