package akihabaramarcket;

import java.sql.*;
import java.util.ArrayList;

public class ProductosDAO {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	Visual vista = new Visual();

	// Constructor: se encarga de conectar con la base de datos al crear el objeto
	public ProductosDAO() {
		// Me conecto a la base de datos usando mi clase Conexionbase
		Conexionbase consultas = new Conexionbase();
		conn = consultas.Conexion();

		// Si la conexión no se ha podido hacer, muestro error
		if (conn == null) {
			System.out.println("error de conexion");
		} else {
			try {
				// Creo un statement para poder lanzar consultas SQL
				st = conn.createStatement();
			} catch (SQLException e) {
				System.out.println("Error al crear statement: " + e.getMessage());
			}
		}
	}

	// Función para añadir un nuevo producto a la base de datos
	public void añadirproducto() {
		try {
			// Pido al usuario los datos del producto
			String nombre = Utilidades.pedirString("dime el nombre del producto: ");
			String categoria = Utilidades.pedirString("dime la categoria del producto: ");
			double precio = Utilidades.pedirdouble("dime el precio del producto: ");
			int stock = Utilidades.pedirEntero("que cantidad de stock tienes: ");

			// Preparo la consulta SQL para insertar un nuevo producto
			String insertar = "INSERT INTO productos(nombre,categoria,precio,stock) VALUES (?, ?, ?, ?)";
			PreparedStatement ins = conn.prepareStatement(insertar);
			ins.setString(1, nombre);
			ins.setString(2, categoria);
			ins.setDouble(3, precio);
			ins.setInt(4, stock);
			ins.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al insertar: " + e.getMessage());
		}
	}

	// Función para obtener un producto por su ID
	public Productos obtenerproducto(int codigo) {
		Productos p1 = null;
		try {
			String busca = "SELECT * FROM productos WHERE id_producto=?";
			PreparedStatement bus = conn.prepareStatement(busca);
			bus.setInt(1, codigo);
			rs = bus.executeQuery();

			// Si encuentra el producto, lo creo como objeto Productos
			while (rs.next()) {
				String nomes = rs.getString("nombre");
				String cate = rs.getString("categoria");
				double precioo = rs.getDouble("precio");
				int cantita = rs.getInt("stock");
				p1 = new Productos(nomes, cate, precioo, cantita);
			}
		} catch (SQLException e) {
			System.out.println("Error al buscar producto: " + e.getMessage());
		}
		return p1;
	}

	// Función para listar todos los productos
	public void listarproductos() {
		ArrayList<Productos> listaproductos = new ArrayList<>();
		try {
			rs = st.executeQuery("SELECT * FROM productos");

			while (rs.next()) {
				int idd = rs.getInt(1);
				String name = rs.getString(2);
				String cat = rs.getString(3);
				double price = rs.getDouble(4);
				int canti = rs.getInt(5);
				Productos nuevo = new Productos(idd, name, cat, price, canti);
				listaproductos.add(nuevo);
			}

			// Mando la lista a la clase Visual para mostrarla
			vista.mostrarLista(listaproductos);
		} catch (SQLException e) {
			System.out.println("Error al listar productos: " + e.getMessage());
		}
	}

	// Función para actualizar un producto
	public boolean actualizar(int codigo1) {
		try {
			// Pregunto qué parte quiere actualizar
			int men;
			System.out.println("que quieres editar");
			System.out.println("1.nombre");
			System.out.println("2.categoria");
			System.out.println("3.precio");
			System.out.println("4.stock");
			men = Utilidades.pedirEntero("selecciones una opcion: ");

			switch (men) {
			case 1:
				String nuevoNom = Utilidades.pedirString("cual es el nuevo nombre del producto");
				String query2 = "UPDATE productos SET nombre=? WHERE id_producto=?";
				PreparedStatement ps = conn.prepareStatement(query2);
				ps.setString(1, nuevoNom);
				ps.setInt(2, codigo1);
				ps.executeUpdate();
				return true;
			case 2:
				String nuevaCat = Utilidades.pedirString("cual es la nueva categoria del producto");
				String query3 = "UPDATE productos SET categoria=? WHERE id_producto=?";
				PreparedStatement ps2 = conn.prepareStatement(query3);
				ps2.setString(1, nuevaCat);
				ps2.setInt(2, codigo1);
				ps2.executeUpdate();
				return true;
			case 3:
				double nuevoPrecio = Utilidades.pedirdouble("cual es el nuevo precio del producto");
				String query4 = "UPDATE productos SET precio=? WHERE id_producto=?";
				PreparedStatement ps3 = conn.prepareStatement(query4);
				ps3.setDouble(1, nuevoPrecio);
				ps3.setInt(2, codigo1);
				ps3.executeUpdate();
				return true;
			case 4:
				int nuevoStock = Utilidades.pedirEntero("cual es el nuevo stock del producto");
				String query5 = "UPDATE productos SET stock=? WHERE id_producto=?";
				PreparedStatement ps4 = conn.prepareStatement(query5);
				ps4.setInt(1, nuevoStock);
				ps4.setInt(2, codigo1);
				ps4.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error al actualizar: " + e.getMessage());
		}
		return false;
	}

	// Función para eliminar un producto por ID
	public boolean eliminar(int codigo) {
		try {
			String quer = "DELETE FROM productos WHERE id_producto=?";
			PreparedStatement eli = conn.prepareStatement(quer);
			eli.setInt(1, codigo);
			eli.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Error al eliminar producto: " + e.getMessage());
			return false;
		}
	}

	// Función para buscar un producto por su nombre
	public Productos mostrarnombre(String nome) {
		try {
			String busq = "SELECT * FROM productos WHERE nombre=?";
			PreparedStatement bu = conn.prepareStatement(busq);
			bu.setString(1, nome);
			rs = bu.executeQuery();

			while (rs.next()) {
				String nomes = rs.getString(2);
				String cate = rs.getString(3);
				double precioo = rs.getDouble(4);
				int cantita = rs.getInt(5);
				return new Productos(nomes, cate, precioo, cantita);
			}
		} catch (SQLException e) {
			System.out.println("Error al buscar por nombre: " + e.getMessage());
		}
		return null;
	}
}
