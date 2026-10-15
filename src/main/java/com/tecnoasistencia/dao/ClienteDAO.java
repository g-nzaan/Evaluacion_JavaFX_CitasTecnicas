/*package com.tecnoasistencia.dao;

import com.tecnoasistencia.conexion.ConexionDB;
import com.tecnoasistencia.modelo.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ClienteDAO {

    public boolean insertarCliente(Cliente cliente){
        String sql = "INSERT INTO cliente (nombres, apellidos, telefono, correo, activo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getNombres());
        }
    }

}*/