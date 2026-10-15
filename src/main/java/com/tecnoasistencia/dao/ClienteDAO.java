package com.tecnoasistencia.dao;

import com.tecnoasistencia.conexion.ConexionDB;
import com.tecnoasistencia.modelo.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ClienteDAO {

    public boolean insertarCliente(Cliente cliente){
        String sql = "INSERT INTO cliente (nombres, apellidos, telefono, correo, activo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getNombres());
            pstmt.setString(2, cliente.getApellidos());
            pstmt.setString(3, cliente.getTelefono());
            pstmt.setString(4, cliente.getCorreo());
            pstmt.setBoolean(5, cliente.isActivo());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e){
            System.err.println("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }

    public ObservableList<Cliente> listarClientes(){
        ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();
        String sql = "SELECT id, nombres, apellidos, telefono, correo, activo FROM cliente";

        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getBoolean("activo")
                );
                listaClientes.add(cliente);
            }
        } catch (SQLException e){
            System.err.println("Error al listar clientes: " + e.getMessage());
        }
        return listaClientes;
    }

    public boolean actualizarCliente(Cliente cliente){
        String sql ="UPDATE cliente SET nombres = ?, apellidos = ?, telefono = ?, correo = ?, activo = ? WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getNombres());
            pstmt.setString(2, cliente.getApellidos());
            pstmt.setString(3, cliente.getTelefono());
            pstmt.setString(4, cliente.getCorreo());
            pstmt.setBoolean(5, cliente.isActivo());
            pstmt.setInt(6, cliente.getId());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e){
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCliente(int clienteId){
        String sqlVerificarCitas = "SELECT COUNT(*) FROM cita_tecnica WHERE cliente_id = ?";
        String sqlEliminar = "DELETE FROM cliente WHERE id = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmtVerificar = conn.prepareStatement(sqlVerificarCitas)){

            pstmtVerificar.setInt(1, clienteId);
            try (ResultSet rs = pstmtVerificar.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0){
                    System.err.println("Acción no permitida: No se puede eliminar un cliente con citas técnicas asociadas.");
                    return false;
                }
            }

            try (PreparedStatement pstmtEliminar = conn.prepareStatement(sqlEliminar)){
                pstmtEliminar.setInt(1, clienteId);
                pstmtEliminar.executeUpdate();
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }

    }

}