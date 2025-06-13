package akihabaramarcket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexionbase {
	private Connection conn; // Objeto para la conexión a la base de datos

	// Método para establecer y devolver la conexión
	public Connection Conexion() {
		try {
			// Carga el driver de MySQL
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Datos de acceso a la base de datos
			String usr = "root";                      // Usuario
			String pwd = "campusfp";                  // Contraseña
			String url = "jdbc:mysql://localhost:3306/akihabara"; // URL de la BD

			// Establece la conexión
			conn = DriverManager.getConnection(url, usr, pwd);
		} catch (Exception e) {
			System.out.println("Error al conectar: " + e.getMessage());
		}
		return conn; // Devuelve la conexión (si tuvo éxito)
	}
}
