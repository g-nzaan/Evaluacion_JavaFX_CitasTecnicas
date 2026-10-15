package com.tecnoasistencia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Punto de entrada principal de la aplicación JavaFX para TecnoAsistencia.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tecnoasistencia/vistas/cliente-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1020, 700);
        stage.setTitle("TecnoAsistencia Nicaragua S.A. - Gestión de Clientes");
        stage.setMinWidth(920);
        stage.setMinHeight(620);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
