package com.tecnoasistencia.controlador;

import com.tecnoasistencia.modelo.Cliente;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador de la vista de clientes (cliente-view.fxml).
 * Configura diseño visual y animaciones con JavaFX nativo puro (sin CSS externo).
 */
public class ClienteController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarDisenoVisualJavaFX();
        configurarAnimacionesJavaFX();
    }

    /**
     * Aplica el diseño visual programáticamente usando las clases nativas de JavaFX
     * (Background, Border, Color, Font, DropShadow), sin recurrir a CSS externo.
     */
    private void configurarDisenoVisualJavaFX() {
        if (rootPane != null) {
            rootPane.setBackground(new Background(new BackgroundFill(Color.web("#f8fafc"), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        if (headerBox != null) {
            LinearGradient gradienteHeader = new LinearGradient(
                    0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.web("#0f172a")),
                    new Stop(1, Color.web("#1e293b"))
            );
            headerBox.setBackground(new Background(new BackgroundFill(gradienteHeader, CornerRadii.EMPTY, Insets.EMPTY)));
        }

        if (lblEmpresa != null) {
            lblEmpresa.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
            lblEmpresa.setTextFill(Color.web("#94a3b8"));
        }
        if (lblTitulo != null) {
            lblTitulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
            lblTitulo.setTextFill(Color.WHITE);
        }

        DropShadow sombraCard = new DropShadow();
        sombraCard.setColor(Color.rgb(0, 0, 0, 0.06));
        sombraCard.setRadius(12);
        sombraCard.setOffsetY(3);

        CornerRadii radioCard = new CornerRadii(10);
        Background fondoCard = new Background(new BackgroundFill(Color.WHITE, radioCard, Insets.EMPTY));
        Border bordeCard = new Border(new BorderStroke(Color.web("#e2e8f0"), BorderStrokeStyle.SOLID, radioCard, new BorderWidths(1)));

        if (panelFormulario != null) {
            panelFormulario.setBackground(fondoCard);
            panelFormulario.setBorder(bordeCard);
            panelFormulario.setEffect(sombraCard);
        }

        if (panelTabla != null) {
            panelTabla.setBackground(fondoCard);
            panelTabla.setBorder(bordeCard);
            panelTabla.setEffect(sombraCard);
        }

        if (lblFormTitulo != null) {
            lblFormTitulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
            lblFormTitulo.setTextFill(Color.web("#1e293b"));
        }
        if (lblFormSubtitulo != null) {
            lblFormSubtitulo.setFont(Font.font("Segoe UI", 12));
            lblFormSubtitulo.setTextFill(Color.web("#64748b"));
        }
        if (lblBuscar != null) {
            lblBuscar.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
            lblBuscar.setTextFill(Color.web("#334155"));
        }
        if (lblContador != null) {
            lblContador.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
            lblContador.setTextFill(Color.web("#64748b"));
        }

        estilarBoton(btnGuardar, "#2563eb", "#1d4ed8");
        estilarBoton(btnActualizar, "#f59e0b", "#d97706");
        estilarBoton(btnEliminar, "#ef4444", "#dc2626");
        estilarBoton(btnLimpiar, "#64748b", "#475569");
        estilarBoton(btnBuscar, "#0284c7", "#0369a1");
        estilarBoton(btnLimpiarBusqueda, "#64748b", "#475569");

        estilarCampoTexto(txtNombres);
        estilarCampoTexto(txtApellidos);
        estilarCampoTexto(txtTelefono);
        estilarCampoTexto(txtCorreo);
        estilarCampoTexto(txtBuscar);

        if (txtId != null) {
            txtId.setBackground(new Background(new BackgroundFill(Color.web("#f1f5f9"), new CornerRadii(5), Insets.EMPTY)));
            txtId.setBorder(new Border(new BorderStroke(Color.web("#e2e8f0"), BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
        }
    }

    private void estilarBoton(Button boton, String colorHex, String colorHoverHex) {
        if (boton == null) return;
        CornerRadii radii = new CornerRadii(6);
        boton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        boton.setTextFill(Color.WHITE);
        boton.setCursor(Cursor.HAND);
        boton.setPadding(new Insets(8, 16, 8, 16));

        Background normalBg = new Background(new BackgroundFill(Color.web(colorHex), radii, Insets.EMPTY));
        Background hoverBg = new Background(new BackgroundFill(Color.web(colorHoverHex), radii, Insets.EMPTY));
        boton.setBackground(normalBg);

        boton.hoverProperty().addListener((obs, oldVal, isHover) -> {
            if (!boton.isDisabled()) {
                boton.setBackground(isHover ? hoverBg : normalBg);
            }
        });
    }

    private void estilarCampoTexto(TextField tf) {
        if (tf == null) return;
        CornerRadii radii = new CornerRadii(5);
        Border normalBorder = new Border(new BorderStroke(Color.web("#cbd5e1"), BorderStrokeStyle.SOLID, radii, new BorderWidths(1)));
        Border focusBorder = new Border(new BorderStroke(Color.web("#2563eb"), BorderStrokeStyle.SOLID, radii, new BorderWidths(1.5)));
        Background bg = new Background(new BackgroundFill(Color.WHITE, radii, Insets.EMPTY));

        tf.setBackground(bg);
        tf.setBorder(normalBorder);
        tf.setPadding(new Insets(7, 10, 7, 10));

        tf.focusedProperty().addListener((obs, oldVal, isFocused) -> {
            tf.setBorder(isFocused ? focusBorder : normalBorder);
        });
    }

    private void configurarAnimacionesJavaFX() {
        agregarAnimacionHover(btnGuardar);
        agregarAnimacionHover(btnActualizar);
        agregarAnimacionHover(btnEliminar);
        agregarAnimacionHover(btnLimpiar);
        agregarAnimacionHover(btnBuscar);
        agregarAnimacionHover(btnLimpiarBusqueda);
    }

    private void agregarAnimacionHover(Button boton) {
        if (boton == null) return;
        ScaleTransition stIn = new ScaleTransition(Duration.millis(120), boton);
        stIn.setToX(1.04);
        stIn.setToY(1.04);

        ScaleTransition stOut = new ScaleTransition(Duration.millis(120), boton);
        stOut.setToX(1.0);
        stOut.setToY(1.0);

        boton.setOnMouseEntered(e -> {
            if (!boton.isDisabled()) {
                stIn.playFromStart();
            }
        });
        boton.setOnMouseExited(e -> {
            stOut.playFromStart();
        });
    }

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
