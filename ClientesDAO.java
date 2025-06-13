package akihabaramarcket;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientesDAO {
	private Connection conn; // Conexión a la base de datos
	private Statement st; // Para ejecutar sentencias SQL
	private ResultSet rs; // Para guardar resultados de consultas
	Visual vista = new Visual(); // Clase para mostrar los datos (interfaz visual)

	// Constructor: conecta con la base de datos
	public ClientesDAO() {
		Conexionbase consultas = new Conexionbase();
		conn = consultas.Conexion(); // Obtiene conexión
		if (conn == null) {
			System.out.println("error de conexion");
		} else {
			try {
				st = conn.createStatement(); // Crea objeto para ejecutar SQL
			} catch (SQLException e) {
				System.out.println("Error al crear statement: " + e.getMessage());
			}
		}
	}

	// Añadir un nuevo cliente a la base de datos
	public void añadircli() {
		try {
			String nom = Utilidades.pedirString("Dime el nombre del usuario: ");
			String correo = Utilidades.pedirEmail("Dime el correo del usuario: ");
			String tel = Utilidades.pedirTelefono("Dime el teléfono del usuario: ");
			String insertar = "INSERT INTO clientes(nombre,email,telefono) VALUES (?,?,?)";
			PreparedStatement ins = conn.prepareStatement(insertar);
			ins.setString(1, nom);
			ins.setString(2, correo);
			ins.setString(3, tel);
			ins.executeUpdate(); // Ejecuta la inserción
		} catch (SQLException e) {
			System.out.println("Error al crear statement: " + e.getMessage());
		}
	}

	// Obtener un cliente por su ID
	public ClienteOtaku obtenerid(int id) {
		ClienteOtaku c = null;
		try {
			String busca = "SELECT * FROM clientes WHERE id_cliente=?";
			PreparedStatement bus = conn.prepareStatement(busca);
			bus.setInt(1, id);
			rs = bus.executeQuery();
			while (rs.next()) {
				String nomes = rs.getString("nombre");
				String core = rs.getString("email");
				String tele = rs.getString("telefono");
				Date fechi = rs.getDate("fecha_registro");
				c = new ClienteOtaku(nomes, core, tele, fechi);
			}
		} catch (SQLException e) {
			System.out.println("Error al crear statement: " + e.getMessage());
		}
		return c;
	}

	// Mostrar la lista de todos los clientes
	public void listacli() {
		ArrayList<ClienteOtaku> listacliente = new ArrayList<>();
		try {
			rs = st.executeQuery("SELECT * FROM clientes");
			while (rs.next()) {
				int idd = rs.getInt(1);
				String name = rs.getString(2);
				String corre = rs.getString(3);
				String telef = rs.getString(4);
				Date fech = rs.getDate(5);
				ClienteOtaku nuevo = new ClienteOtaku(idd, name, corre, telef, fech);
				listacliente.add(nuevo);
			}
			vista.mostrarlistacli(listacliente);
		} catch (SQLException e) {
			System.out.println("Error al listar clientes: " + e.getMessage());
		}
	}

	// Actualizar datos de un cliente según su ID
	public boolean actualizar(int idd) {
		try {
			int men;
			System.out.println("¿Qué quieres editar?");
			System.out.println("1. Nombre");
			System.out.println("2. Correo");
			System.out.println("3. Teléfono");
			men = Utilidades.pedirEntero("Selecciona una opción: ");

			switch (men) {
			case 1:
				String nuenom = Utilidades.pedirString("¿Cuál es el nuevo nombre del cliente?: ");
				String query2 = "UPDATE clientes SET nombre=? WHERE id_cliente=?";
				PreparedStatement ps = conn.prepareStatement(query2);
				ps.setString(1, nuenom);
				ps.setInt(2, idd);
				ps.executeUpdate();
				return true;

			case 2:
				String nuecor = Utilidades.pedirEmail("¿Cuál es el nuevo correo del cliente?: ");
				String query3 = "UPDATE clientes SET email=? WHERE id_cliente=?";
				PreparedStatement pss = conn.prepareStatement(query3);
				pss.setString(1, nuecor);
				pss.setInt(2, idd);
				pss.executeUpdate();
				return true;

			case 3:
				String nuetel = Utilidades.pedirTelefono("¿Cuál es el nuevo teléfono del cliente?: ");
				String query4 = "UPDATE clientes SET telefono=? WHERE id_cliente=?";
				PreparedStatement psss = conn.prepareStatement(query4);
				psss.setString(1, nuetel);
				psss.setInt(2, idd);
				psss.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error al actualizar: " + e.getMessage());
		}
		return false;
	}

	// Eliminar un cliente por su ID
	public boolean eliminado(int id) {
		try {
			String quer = "DELETE FROM clientes WHERE id_cliente=?";
			PreparedStatement eli = conn.prepareStatement(quer);
			eli.setInt(1, id);
			eli.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Error al eliminar cliente: " + e.getMessage());
			return false;
		}
	}

	// Buscar un cliente por su email
	public ClienteOtaku buscarporemail(String corre) {
		try {
			String busq = "SELECT * FROM clientes WHERE email=?";
			PreparedStatement bu = conn.prepareStatement(busq);
			bu.setString(1, corre);
			rs = bu.executeQuery();
			while (rs.next()) {
				String nomes = rs.getString(2);
				String coreo = rs.getString(3);
				String telefo = rs.getString(4);
				Date f = rs.getDate(5);
				return new ClienteOtaku(nomes, coreo, telefo, f);
			}
		} catch (SQLException e) {
			System.out.println("Error al buscar por email: " + e.getMessage());
		}
		return null;
	}
}