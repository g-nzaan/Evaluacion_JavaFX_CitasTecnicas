package com.tecnoasistencia.dao;

import com.tecnoasistencia.conexion.ConexionDB;
import com.tecnoasistencia.modelo.CitaTecnica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

/** Implementación de apoyo para las clases vacías del integrante 3. */
public class CitaTecnicaDAO {
    private Connection abrirConexion() throws SQLException {
        Connection conexion = ConexionDB.conectar();
        if (conexion == null) throw new SQLException("No se pudo conectar con PostgreSQL.");
        return conexion;
    }
    public ObservableList<CitaTecnica> listarCitas() throws SQLException {
        String sql = "SELECT ct.id, ct.cliente_id, c.nombres || ' ' || c.apellidos AS nombre_cliente, "
                + "ct.fecha, ct.hora, ct.servicio, ct.estado FROM cita_tecnica ct "
                + "JOIN cliente c ON c.id = ct.cliente_id ORDER BY ct.fecha, ct.hora, ct.id";
        ObservableList<CitaTecnica> citas = FXCollections.observableArrayList();
        try (Connection conn = abrirConexion(); PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) citas.add(new CitaTecnica(rs.getInt("id"), rs.getInt("cliente_id"),
                    rs.getString("nombre_cliente"), rs.getDate("fecha").toLocalDate(),
                    rs.getTime("hora").toLocalTime(), rs.getString("servicio"), rs.getString("estado")));
        }
        return citas;
    }
    public boolean insertarCita(CitaTecnica cita) throws SQLException {
        String sql = "INSERT INTO cita_tecnica (cliente_id, fecha, hora, servicio, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = abrirConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            asignarCampos(ps, cita);
            return ps.executeUpdate() == 1;
        }
    }
    public boolean actualizarCita(CitaTecnica cita) throws SQLException {
        String sql = "UPDATE cita_tecnica SET cliente_id = ?, fecha = ?, hora = ?, servicio = ?, estado = ? WHERE id = ?";
        try (Connection conn = abrirConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            asignarCampos(ps, cita);
            ps.setInt(6, cita.getId());
            return ps.executeUpdate() == 1;
        }
    }
    public boolean eliminarCita(int id) throws SQLException {
        try (Connection conn = abrirConexion();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM cita_tecnica WHERE id = ?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }
    private void asignarCampos(PreparedStatement ps, CitaTecnica cita) throws SQLException {
        ps.setInt(1, cita.getClienteId());
        ps.setDate(2, Date.valueOf(cita.getFecha()));
        ps.setTime(3, Time.valueOf(cita.getHora()));
        ps.setString(4, cita.getServicio());
        ps.setString(5, cita.getEstado());
    }
}