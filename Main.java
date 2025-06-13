package akihabaramarcket;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import akihabaramarcket.Productos;
import akihabaramarcket.Utilidades;

public class Main {
	public static void main(String[] args) {
		// Declaro variables necesarias
		int opcion;
		int cosi;
		int opci;

		// Creo objetos para acceder a los métodos de cada clase
		ProductosDAO funci = new ProductosDAO(); // para productos
		Visual viso = new Visual(); // para mostrar datos
		Interfazconsola inter = new Interfazconsola(); // para funcionalidades avanzadas con IA
		Llmservice llamadaIA = new Llmservice(); // para interactuar con el modelo de lenguaje
		ClientesDAO cli = new ClientesDAO(); // para clientes

		// El menú principal se repite hasta que el usuario pulse la opción 3 (salir)
		do {
			viso.menu1(); // muestro el menú principal
			cosi = Utilidades.pedirEntero("seleccione una opcion: ");

			// Si elige 1, entramos en el menú de clientes
			if (cosi == 1) {
				do {
					viso.menucli(); // muestro menú de clientes
					opci = Utilidades.pedirEntero("seleccione una opcion: ");

					switch (opci) {
					case 1: // Añadir cliente
						cli.añadircli();
						break;
					case 2: // Buscar cliente por ID
						int codigo = Utilidades.pedirEntero("dime el id del cliente que buscas: ");
						viso.mostrarcli(cli.obtenerid(codigo));
						break;
					case 3: // Listar todos los clientes
						cli.listacli();
						break;
					case 4: // Actualizar cliente
						int id = Utilidades.pedirEntero("dime el id del cliente que quieres actualizar: ");
						if (cli.actualizar(id)) {
							System.out.println("se ha actualizado el cliente");
						} else {
							System.out.println("error");
						}
						break;
					case 5: // Eliminar cliente
						int elimi = Utilidades.pedirEntero("dime el id del cliente a eliminar: ");
						if (cli.eliminado(elimi)) {
							System.out.println("se ha eliminado correctamente");
						} else {
							System.out.println("error");
						}
						break;
					case 6: // Buscar cliente por correo
						String correo = Utilidades.pedirString("dime el correo del usuario que buscas: ");
						viso.mostrarcli(cli.buscarporemail(correo));
						break;
					case 7: // Salida del menú de clientes
						System.out.println("ADIOS !!!!");
						break;
					default:
						// Por si elige una opción que no existe
						System.out.println("opncion no valida selecciones una opcion valida");
					}
				} while (opci != 7); // Vuelve al menú hasta que se pulse 7
			}

			// Si elige 2, entra al menú de productos
			if (cosi == 2) {
				do {
					viso.menu(); // muestro el menú de productos
					opcion = Utilidades.pedirEntero("seleccione una opcion: ");

					switch (opcion) {
					case 1: // Añadir producto
						funci.añadirproducto();
						break;
					case 2: // Buscar producto por código
						int codigo = Utilidades.pedirEntero("dime el codigo del producto que buscas");
						viso.mostrarproductos(funci.obtenerproducto(codigo));
						break;
					case 3: // Listar todos los productos
						funci.listarproductos();
						break;
					case 4: // Actualizar producto
						int codigo1 = Utilidades.pedirEntero("dime el codigo del producto que buscas");
						if (funci.actualizar(codigo1)) {
							System.out.println("se ha actualizado el producto");
						} else {
							System.out.println("error");
						}
						break;
					case 5: // Eliminar producto
						int codigo2 = Utilidades.pedirEntero("dime el codigo del producto que quieres eliminar: ");
						if (funci.eliminar(codigo2)) {
							System.out.println("se ha eliminado el producto");
						} else {
							System.out.println("error");
						}
						break;
					case 6: // Buscar producto por nombre
						String nome = Utilidades.pedirString("que producto buscas: ");
						viso.mostrarproductos(funci.mostrarnombre(nome));
						break;
					case 7: // Hacerle una pregunta a la IA
						String pregunta = Utilidades
								.pedirString("Escribe tu pregunta sobre productos akihabaramarket: ");
						try {
							String respuesta = llamadaIA.enviarPregunta(pregunta);
							viso.mostrarRespuestaIA(respuesta);
						} catch (IOException e) {
							System.err.println("Error: " + e.getMessage());
						}
						inter.preguntaapi(llamadaIA);
						break;
					case 8: // Generar descripción del producto usando IA
						inter.generarDescripcionProductoIA(llamadaIA, funci);
						break;
					case 9: // Sugerir categoría usando IA
						inter.sugerirCategoriaIA(llamadaIA);
						break;
					case 10: // Salir del menú de productos
						System.out.println("adiosss!!!");
						break;
					default:
						System.out.println("opncion no valida selecciones una opcion valida");
					}
				} while (opcion != 10); // Vuelve al menú hasta que pulse 10 (salir)
			}

			// Validación básica por si elige algo fuera de 1, 2 o 3
			if (cosi != 1 && cosi != 2 && cosi != 3) {
				System.out.println("opncion no valida selecciones una opcion valida");
			}
		} while (cosi != 3); // Sale del programa si pulsa 3 en el menú principal
	}
}
