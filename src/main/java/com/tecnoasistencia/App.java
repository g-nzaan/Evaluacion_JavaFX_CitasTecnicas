
package com.tecnoasistencia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Punto de entrada principal de la aplicación JavaFX para TecnoAsistencia.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        TabPane panel = new TabPane();
        panel.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        panel.getTabs().add(new Tab("Clientes", cargarVista("cliente-view.fxml")));
        panel.getTabs().add(new Tab("Citas técnicas", cargarVista("cita-view.fxml")));
        Scene scene = new Scene(panel, 1200, 760);
        stage.setTitle("TecnoAsistencia Nicaragua S.A. - Clientes y Citas Técnicas");
        stage.setMinWidth(1100);
        stage.setMinHeight(700);
        stage.setScene(scene);
        stage.show();
    }

    private Parent cargarVista(String nombre) throws IOException {
        return new FXMLLoader(getClass().getResource("/com/tecnoasistencia/vistas/" + nombre)).load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}