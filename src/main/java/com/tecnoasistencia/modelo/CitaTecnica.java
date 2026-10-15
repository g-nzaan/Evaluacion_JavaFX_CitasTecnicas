package com.tecnoasistencia.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Representa la entidad de Cita Técnica en el sistema TecnoAsistencia.
 * Mantiene correspondencia con la tabla 'cita_tecnica' en la base de datos PostgreSQL,
 * e incluye el nombre del cliente para facilitar el enlace con vistas JavaFX (TableView).
 */
public class CitaTecnica {

    private static final DateTimeFormatter FORMATO_FECHA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private int id;
    private int clienteId;
    private String clienteNombre; // Campo informativo obtenido vía JOIN con la tabla cliente
    private LocalDateTime fechaHora;
    private String motivo;
    private EstadoCita estado;

    /**
     * Constructor por defecto. Inicializa el estado en Pendiente por convención.
     */
    public CitaTecnica() {
        this.estado = EstadoCita.PENDIENTE;
    }

    /**
     * Constructor para registrar nuevas citas (sin ID ni nombre de cliente aún asignados).
     *
     * @param clienteId Identificador del cliente existente.
     * @param fechaHora Fecha y hora programada para la cita.
     * @param motivo Descripción o causa de la solicitud técnica.
     * @param estado Estado inicial de la cita.
     */
    public CitaTecnica(int clienteId, LocalDateTime fechaHora, String motivo, EstadoCita estado) {
        this.clienteId = clienteId;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estado = estado != null ? estado : EstadoCita.PENDIENTE;
    }

    /**
     * Constructor completo para citas recuperadas de la base de datos sin JOIN de cliente.
     *
     * @param id Identificador único de la cita técnica.
     * @param clienteId Identificador del cliente asociado.
     * @param fechaHora Fecha y hora programada.
     * @param motivo Descripción o causa del servicio técnico.
     * @param estado Estado actual de la cita.
     */
    public CitaTecnica(int id, int clienteId, LocalDateTime fechaHora, String motivo, EstadoCita estado) {
        this.id = id;
        this.clienteId = clienteId;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estado = estado != null ? estado : EstadoCita.PENDIENTE;
    }

    /**
     * Constructor completo para consultas con JOIN (incluye el nombre completo del cliente).
     *
     * @param id Identificador único de la cita técnica.
     * @param clienteId Identificador del cliente asociado.
     * @param clienteNombre Nombre y apellido concatenados del cliente.
     * @param fechaHora Fecha y hora programada.
     * @param motivo Descripción del motivo del servicio técnico.
     * @param estado Estado actual de la cita.
     */
    public CitaTecnica(int id, int clienteId, String clienteNombre, LocalDateTime fechaHora, String motivo, EstadoCita estado) {
        this.id = id;
        this.clienteId = clienteId;
        this.clienteNombre = clienteNombre;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estado = estado != null ? estado : EstadoCita.PENDIENTE;
    }

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }

    // ==========================================
    // MÉTODOS DE CONVENIENCIA PARA JAVAFX
    // ==========================================

    /**
     * Retorna la representación en texto del estado para PropertyValueFactory de JavaFX.
     */
    public String getEstadoStr() {
        return estado != null ? estado.getDescripcion() : "";
    }

    /**
     * Asigna el estado a partir de su descripción textual, validándolo contra EstadoCita.
     */
    public void setEstadoStr(String estadoStr) {
        this.estado = EstadoCita.fromDescripcion(estadoStr);
    }

    /**
     * Retorna la fecha y hora formateada en formato legible (dd/MM/yyyy HH:mm) para TableView.
     */
    public String getFechaHoraFormateada() {
        return fechaHora != null ? fechaHora.format(FORMATO_FECHA_HORA) : "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CitaTecnica that = (CitaTecnica) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CitaTecnica{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", clienteNombre='" + clienteNombre + '\'' +
                ", fechaHora=" + getFechaHoraFormateada() +
                ", motivo='" + motivo + '\'' +
                ", estado=" + (estado != null ? estado.getDescripcion() : "null") +
                '}';
    }
}
