# Evaluacion_JavaFX_CitasTecnicas
Sistema de gestión de citas de atención técnica — JavaFX y JDBC (PostgreSQL).

## Integrantes del Equipo (Grupo 5)

| Nombre Completo | Usuario GitHub | Rol | Distribución de Responsabilidades |
| :--- | :--- | :--- | :--- |
| Andrés Sebastián González Maradiaga | [g-nzaan](https://github.com/g-nzaan) | Integrante 1 | Conexión JDBC centralizada, Modelo Cliente, CRUD ClienteDAO y validación de eliminación. |
| William Antonio García García | [Wigar03](https://github.com/Wigar03) | Integrante 2 | Interfaz gráfica FXML/TableView de Clientes, enlace de datos con ClienteDAO, formulario CRUD, validaciones y búsqueda dinámica (RF02, RF05, RF08, RF09). |
|  Caleb Jordan Tardencilla Alvarado | [CTardencilla](https://github.com/CTardencilla) | Integrante 3 | Modelo y DAO de Citas Técnicas, relación Cliente-Citas y filtros. |
| Rafael Hernández Sánchez | [rafaelhs07](https://github.com/rafaelhs07)  | Integrante 4 | Interfaz gráfica FXML de Citas Técnicas, validaciones y reglas de negocio. |

## Requisitos y Configuración de PostgreSQL

1. **Base de Datos:** `gestion_citas`
2. **Motor:** PostgreSQL (puerto 5432)
3. **Credenciales locales por defecto:**
   - **Usuario:** `admin`
   - **Contraseña:** `1234`
   *(Configurables centralizadamente en `ConexionDB.java`)*
4. **Ejecución del proyecto:**
   ```bash
   mvn clean compile javafx:run
   ```
