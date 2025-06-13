package akihabaramarcket;

import java.util.ArrayList;

public class Visual {

	// Muestra la información de un producto individual
	public void mostrarproductos(Productos p) {
		// Si el producto no existe, informo al usuario
		if (p == null) {
			System.out.println("no se encuentra el producto");
			return;
		}
		// Si el producto existe, muestro sus datos bien formateados
		System.out.println("esta es la informacion del producto encontrado");
		System.out.printf("%-15s %-15s %-10s %-10s\n", "NOMBRE", "CATEGORÍA", "PRECIO", "STOCK");
		System.out.printf("%-15s %-15s %-10.2f %-10d\n", 
			p.getNombre(), 
			p.getCategoria(), 
			p.getPrecio(), 
			p.getStock());
	}

	// Muestra una lista de productos en tabla (usando ArrayList)
	public void mostrarLista(ArrayList<Productos> listaProductos) {
	    System.out.println("---- LISTA DE PRODUCTOS ----");
	    System.out.printf("%-30s %-20s %-10s %-10s\n", "NOMBRE", "CATEGORÍA", "PRECIO", "STOCK");
	    System.out.println("--------------------------------------------------------------------------");

	    for (Productos p : listaProductos) {
	        System.out.printf("%-30s %-20s %-10.2f %-10d\n",
	            p.getNombre(),
	            p.getCategoria(),
	            p.getPrecio(),
	            p.getStock());
	    }

	    System.out.println("--------------------------------------------------------------------------");
	}

	// Muestra lista de clientes
	public void mostrarlistacli(ArrayList<ClienteOtaku> listacli) {
	    System.out.println("---- LISTA DE CLIENTES ----");
	    System.out.printf("%-20s %-30s %-15s %-15s\n", "NOMBRE", "EMAIL", "TELÉFONO", "FECHA REGISTRO");
	    System.out.println("--------------------------------------------------------------------------------------");

	    for (ClienteOtaku cli : listacli) {
	        System.out.printf("%-20s %-30s %-15s %-15s\n",
	            cli.getNombre(),
	            cli.getEmail(),
	            cli.getTelefono(),
	            cli.getFecha_regi().toString());
	    }

	    System.out.println("--------------------------------------------------------------------------------------");
	}

	// Muestra un cliente individual
	public void mostrarcli(ClienteOtaku cli) {
	    if (cli == null) {
	        System.out.println("No se encuentra el cliente");
	        return;
	    }
	    System.out.println("Esta es la información del cliente encontrado:");
	    System.out.printf("%-15s %-30s %-15s %-15s\n", "NOMBRE", "EMAIL", "TELÉFONO", "FECHA REGISTRO");
	    System.out.printf("%-15s %-30s %-15s %-15s\n",
	        cli.getNombre(),
	        cli.getEmail(),
	        cli.getTelefono(),
	        cli.getFecha_regi().toString());
	}

	// Muestra respuestas generadas por la IA (por ejemplo, descripciones o categorías)
	public void mostrarRespuestaIA(String respuesta) {
		System.out.println("\n=== RESPUESTA DEL ASISTENTE ===");
		System.out.println(respuesta);
		System.out.println("=======================\n");
	}

	// Menú principal para gestionar productos
	public void menu() {
		System.out.println("------ MENU -------");
		System.out.println("1. AGREGAR PRODUCTO");
		System.out.println("2. BUSCAR PRODUCTO");
		System.out.println("3. LISTAR PRODUCTOS");
		System.out.println("4. ACTUALIZAR PRODUCTO");
		System.out.println("5. ELIMINAR");
		System.out.println("6. BUSCAR PRODUCTO POR NOMBRE");
		System.out.println("7. UTILIZAR ASISTENTE");
		System.out.println("8. GENERAR DESCRIPCION");
		System.out.println("9. GENERAR CATEGORIA");
		System.out.println("10. SALIR");
	}

	// Menú inicial para elegir entre clientes o productos
	public void menu1() {
		System.out.println("---- SELECCIONE QUE QUIERE UTILIZAR ----");
		System.out.println("1. CLIENTES");
		System.out.println("2. PRODUCTOS");
		System.out.println("3. SALIR");
	}

	// Menú específico para clientes
	public void menucli() {
		System.out.println("---- MENU ----");
		System.out.println("1. AGREGAR CLIENTE");
		System.out.println("2. OBTENER CLIENTE POR ID");
		System.out.println("3. MOSTRAR CLIENTES");
		System.out.println("4. ACTUALIZAR CLIENTE");
		System.out.println("5. ELIMINAR CLIENTE");
		System.out.println("6. BUSCAR CLIENTE POR EMAIL");
		System.out.println("7 SALIR");
	}
}
