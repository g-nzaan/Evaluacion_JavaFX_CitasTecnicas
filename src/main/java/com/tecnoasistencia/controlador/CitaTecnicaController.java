package com.tecnoasistencia.controlador;

import com.tecnoasistencia.dao.CitaTecnicaDAO;
import com.tecnoasistencia.dao.ClienteDAO;
import com.tecnoasistencia.modelo.CitaTecnica;
import com.tecnoasistencia.modelo.Cliente;
import com.tecnoasistencia.utilidad.Alertas;
import com.tecnoasistencia.utilidad.Validaciones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/** RF03, RF04, RF06, RF07 y RF08: formulario, tabla, filtros y mensajes. */
public class CitaTecnicaController {
    @FXML private ComboBox<Cliente> cmbCliente;
    @FXML private DatePicker dpFecha;
    @FXML private TextField txtHora;
    @FXML private TextField txtServicio;
    @FXML private ComboBox<String> cmbEstado;
    @FXML private TextField txtId;
    @FXML private ComboBox<String> cmbFiltroEstado;
    @FXML private DatePicker dpFiltroFecha;
    @FXML private TableView<CitaTecnica> tblCitas;
    @FXML private TableColumn<CitaTecnica, Integer> colId;
    @FXML private TableColumn<CitaTecnica, String> colCliente;
    @FXML private TableColumn<CitaTecnica, LocalDate> colFecha;
    @FXML private TableColumn<CitaTecnica, LocalTime> colHora;
    @FXML private TableColumn<CitaTecnica, String> colServicio;
    @FXML private TableColumn<CitaTecnica, String> colEstado;
    @FXML private Button btnGuardar;
    @FXML private Button btnActualizar;
    @FXML private Button btnEliminar;
    @FXML private Label lblContador;

    private final CitaTecnicaDAO citaDAO = new CitaTecnicaDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final ObservableList<CitaTecnica> citas = FXCollections.observableArrayList();
    private final FilteredList<CitaTecnica> filtradas = new FilteredList<>(citas, cita -> true);
    private int idSeleccionado;

    @FXML
    public void initialize() {
        cmbEstado.getItems().setAll(Validaciones.ESTADOS);
        cmbFiltroEstado.getItems().add("Todos");
        cmbFiltroEstado.getItems().addAll(Validaciones.ESTADOS);
        cmbFiltroEstado.setValue("Todos");
        // Se usa el calendario para evitar texto inválido pendiente de confirmar.
        dpFecha.setEditable(false);
        dpFiltroFecha.setEditable(false);
        cmbCliente.setCellFactory(lista -> celdaCliente());
        cmbCliente.setButtonCell(celdaCliente());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colServicio.setCellValueFactory(new PropertyValueFactory<>("servicio"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colFecha.setCellFactory(c -> new TableCell<>() {
            @Override protected void updateItem(LocalDate valor, boolean empty) {
                super.updateItem(valor, empty);
                setText(empty || valor == null ? null : valor.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        });
        colHora.setCellFactory(c -> new TableCell<>() {
            @Override protected void updateItem(LocalTime valor, boolean empty) {
                super.updateItem(valor, empty);
                setText(empty || valor == null ? null : valor.format(Validaciones.HORA));
            }
        });
        SortedList<CitaTecnica> ordenadas = new SortedList<>(filtradas);
        ordenadas.comparatorProperty().bind(tblCitas.comparatorProperty());
        tblCitas.setItems(ordenadas);
        tblCitas.setPlaceholder(new Label("No hay citas para los filtros seleccionados."));
        tblCitas.getSelectionModel().selectedItemProperty().addListener((obs, anterior, actual) -> {
            if (actual != null) cargarFormulario(actual);
        });
        cmbFiltroEstado.valueProperty().addListener((obs, anterior, actual) -> aplicarFiltros());
        dpFiltroFecha.valueProperty().addListener((obs, anterior, actual) -> aplicarFiltros());
        limpiarFormulario();
        cargarDatos();
    }

    private ListCell<Cliente> celdaCliente() {
        return new ListCell<>() {
            @Override protected void updateItem(Cliente cliente, boolean empty) {
                super.updateItem(cliente, empty);
                setText(empty || cliente == null ? null : cliente.getId() + " - " + cliente
                        + (cliente.isActivo() ? "" : " (inactivo)"));
            }
        };
    }

    /** Refresca también el ComboBox después de registrar clientes en la otra pestaña. */
    @FXML
    public void cargarDatos() {
        try {
            ObservableList<Cliente> clientesActuales = clienteDAO.listarClientes();
            ObservableList<CitaTecnica> citasActuales = citaDAO.listarCitas();
            limpiarFormulario();
            cmbCliente.getItems().setAll(clientesActuales);
            citas.setAll(citasActuales);
            aplicarFiltros();
        } catch (SQLException | IllegalStateException e) {
            Alertas.errorDatos(e);
        }
    }

    private void cargarFormulario(CitaTecnica cita) {
        idSeleccionado = cita.getId();
        txtId.setText(String.valueOf(cita.getId()));
        cmbCliente.setValue(cmbCliente.getItems().stream()
                .filter(c -> c.getId() == cita.getClienteId()).findFirst().orElse(null));
        dpFecha.setValue(cita.getFecha());
        txtHora.setText(cita.getHora().format(Validaciones.HORA));
        txtServicio.setText(cita.getServicio());
        cmbEstado.setValue(cita.getEstado());
        btnGuardar.setDisable(true);
        btnActualizar.setDisable(false);
        btnEliminar.setDisable(false);
    }

    private CitaTecnica leerFormulario() {
        Cliente cliente = cmbCliente.getValue();
        if (cliente == null) throw new IllegalArgumentException("Seleccione un cliente existente.");
        if (dpFecha.getValue() == null) throw new IllegalArgumentException("Seleccione la fecha de la cita.");
        LocalTime hora = Validaciones.leerHora(txtHora.getText());
        String servicio = Validaciones.textoObligatorio(txtServicio.getText(), "El servicio", 200);
        if (cmbEstado.getValue() == null || !Validaciones.ESTADOS.contains(cmbEstado.getValue()))
            throw new IllegalArgumentException("Seleccione un estado válido.");
        return new CitaTecnica(idSeleccionado, cliente.getId(), cliente.toString(), dpFecha.getValue(),
                hora, servicio, cmbEstado.getValue());
    }

    @FXML private void handleGuardar() { guardar(false); }
    @FXML private void handleActualizar() { guardar(true); }

    private void guardar(boolean actualizar) {
        try {
            if (actualizar && idSeleccionado == 0)
                throw new IllegalArgumentException("Seleccione una cita de la tabla.");
            CitaTecnica cita = leerFormulario();
            if (!Alertas.confirmar(actualizar ? "Actualizar cita" : "Registrar cita",
                    "¿Desea " + (actualizar ? "actualizar" : "registrar") + " la cita de "
                            + cita.getNombreCliente() + " para el " + cita.getFecha() + " a las " + cita.getHora() + "?")) return;
            boolean guardado = actualizar ? citaDAO.actualizarCita(cita) : citaDAO.insertarCita(cita);
            if (!guardado) {
                Alertas.mostrar(Alert.AlertType.WARNING, "Registro no encontrado", "La cita ya no existe. Recargue la tabla.");
                return;
            }
            Alertas.mostrar(Alert.AlertType.INFORMATION, "Operación exitosa", "Cita guardada correctamente.");
            limpiarFormulario();
            cargarDatos();
        } catch (IllegalArgumentException e) {
            Alertas.mostrar(Alert.AlertType.WARNING, "Revise el formulario", e.getMessage());
        } catch (SQLException e) {
            Alertas.errorDatos(e);
        }
    }

    @FXML private void handleEliminar() {
        if (idSeleccionado == 0) {
            Alertas.mostrar(Alert.AlertType.WARNING, "Selección requerida", "Seleccione una cita de la tabla.");
            return;
        }
        if (!Alertas.confirmar("Eliminar cita", "¿Desea eliminar permanentemente la cita " + idSeleccionado + "?")) return;
        try {
            if (citaDAO.eliminarCita(idSeleccionado)) {
                Alertas.mostrar(Alert.AlertType.INFORMATION, "Operación exitosa", "Cita eliminada correctamente.");
                limpiarFormulario();
                cargarDatos();
            } else {
                Alertas.mostrar(Alert.AlertType.WARNING, "Registro no encontrado", "La cita ya no existe. Recargue la tabla.");
            }
        } catch (SQLException e) { Alertas.errorDatos(e); }
    }

    @FXML private void limpiarFormulario() {
        tblCitas.getSelectionModel().clearSelection();
        idSeleccionado = 0;
        txtId.clear();
        cmbCliente.setValue(null);
        dpFecha.setValue(null);
        txtHora.clear();
        txtServicio.clear();
        cmbEstado.setValue("Pendiente");
        btnGuardar.setDisable(false);
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
    }

    @FXML private void aplicarFiltros() {
        String estado = cmbFiltroEstado.getValue();
        LocalDate fecha = dpFiltroFecha.getValue();
        limpiarFormulario();
        filtradas.setPredicate(cita -> (estado == null || "Todos".equals(estado) || estado.equals(cita.getEstado()))
                && (fecha == null || fecha.equals(cita.getFecha())));
        lblContador.setText("Citas mostradas: " + filtradas.size() + " de " + citas.size());
    }

    @FXML private void handleLimpiarFiltros() {
        cmbFiltroEstado.setValue("Todos");
        dpFiltroFecha.setValue(null);
        aplicarFiltros();
    }
}