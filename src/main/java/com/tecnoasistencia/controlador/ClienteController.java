package com.tecnoasistencia.controlador;

import com.tecnoasistencia.modelo.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Controlador de la vista de clientes (cliente-view.fxml).
 */
public class ClienteController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private VBox headerBox;

    @FXML
    private Label lblEmpresa;

    @FXML
    private Label lblTitulo;

    @FXML
    private VBox panelFormulario;

    @FXML
    private Label lblFormTitulo;

    @FXML
    private Label lblFormSubtitulo;

    @FXML
    private Label lblId;

    @FXML
    private Label lblNombres;

    @FXML
    private Label lblApellidos;

    @FXML
    private Label lblTelefono;

    @FXML
    private Label lblCorreo;

    @FXML
    private Label lblEstado;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombres;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtCorreo;

    @FXML
    private CheckBox chkActivo;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnLimpiar;

    @FXML
    private VBox panelTabla;

    @FXML
    private Label lblBuscar;

    @FXML
    private TextField txtBuscar;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnLimpiarBusqueda;

    @FXML
    private TableView<Cliente> tblClientes;

    @FXML
    private TableColumn<Cliente, Integer> colId;

    @FXML
    private TableColumn<Cliente, String> colNombres;

    @FXML
    private TableColumn<Cliente, String> colApellidos;

    @FXML
    private TableColumn<Cliente, String> colTelefono;

    @FXML
    private TableColumn<Cliente, String> colCorreo;

    @FXML
    private TableColumn<Cliente, Boolean> colActivo;

    @FXML
    private Label lblContador;

    @FXML
    private void handleGuardar(ActionEvent event) {
    }

    @FXML
    private void handleActualizar(ActionEvent event) {
    }

    @FXML
    private void handleEliminar(ActionEvent event) {
    }

    @FXML
    private void handleLimpiar(ActionEvent event) {
    }

    @FXML
    private void handleBuscar(ActionEvent event) {
    }

    @FXML
    private void handleLimpiarBusqueda(ActionEvent event) {
    }
}
