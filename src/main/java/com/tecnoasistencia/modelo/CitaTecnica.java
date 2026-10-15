package com.tecnoasistencia.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

/** Contrato de integración propuesto para coordinar con el integrante 3. */
public class CitaTecnica {
    private int id;
    private int clienteId;
    private String nombreCliente;
    private LocalDate fecha;
    private LocalTime hora;
    private String servicio;
    private String estado;

    public CitaTecnica() { }
    public CitaTecnica(int id, int clienteId, String nombreCliente, LocalDate fecha,
                       LocalTime hora, String servicio, String estado) {
        this.id = id;
        this.clienteId = clienteId;
        this.nombreCliente = nombreCliente;
        this.fecha = fecha;
        this.hora = hora;
        this.servicio = servicio;
        this.estado = estado;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
    public String getServicio() { return servicio; }
    public void setServicio(String servicio) { this.servicio = servicio; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}