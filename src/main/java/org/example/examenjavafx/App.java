package org.example.examenjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.examenjavafx.utils.JavaFXUtil;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        JavaFXUtil.initStage(stage);
        JavaFXUtil.setScene("/org/example/examenjavafx/main-view.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}

