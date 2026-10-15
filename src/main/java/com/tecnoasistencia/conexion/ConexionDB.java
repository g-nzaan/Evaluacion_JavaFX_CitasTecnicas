package com.tecnoasistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionDB {

    private static final String URL = "jdbc:postgresql://localhost:5432/gestion_citas";
    private static final String USER = "admin";
    private static final String PASSWORD = "1234";

    public static Connection conectar(){
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            try (Statement stmt = conexion.createStatement()){
                stmt.execute("SELECT 1");
            }
        } catch (SQLException e){
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }
}
