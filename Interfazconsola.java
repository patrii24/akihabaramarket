package akihabaramarcket;

import java.io.IOException;

public class Interfazconsola {

	// Método para hacer preguntas a la IA
	public void preguntaapi(Llmservice llamadaIA) {
		Visual visual = new Visual();
		System.out.println("\nEscribe 'salir' para volver al menú principal");

		boolean volverAlMenu = false;
		while (!volverAlMenu) {
			String pregunta = Utilidades.pedirString("\nTú: ");

			if ("salir".equalsIgnoreCase(pregunta.trim())) {
				volverAlMenu = true;
				System.out.println("Volviendo al menú principal...");
			} else {
				try {
					// Enviamos la pregunta a la IA y mostramos su respuesta
					String respuesta = llamadaIA.enviarPregunta(pregunta);
					visual.mostrarRespuestaIA(respuesta);
				} catch (IOException e) {
					System.err.println("Error: " + e.getMessage());
				}
			}
		}
	}

	// Método para generar una descripción con IA para un producto
	public void generarDescripcionProductoIA(Llmservice llamadaIA, ProductosDAO productosDao) {
		while (true) {
			System.out.println("\n GENERADOR DE DESCRIPCIONES CON IA ");
			System.out.println("1. Generar nueva descripción");
			System.out.println("2. Volver al menú principal");

			int opcion = Utilidades.pedirEntero("\nSeleccione: ");

			if (opcion == 2)
				return; // Salir al menú

			if (opcion != 1) {
				System.out.println(" Opción no válida");
				continue;
			}

			// Buscar producto por ID
			int id = Utilidades.pedirEntero("Introduce el ID del producto: ");
			Productos producto = productosDao.obtenerproducto(id);

			if (producto == null) {
				System.out.println(" No se encontró un producto con ese ID");
				continue;
			}

			// Generar prompt para la IA
			String prompt = "Genera una descripción de marketing breve y atractiva para el producto otaku: "
					+ producto.getNombre() + " de la categoría " + producto.getCategoria() + ". Máximo 10 palabras.";

			try {
				System.out.println("\n Generando descripción...");
				String descripcion = llamadaIA.enviarPregunta(prompt);
				System.out.println("\n Descripción generada para " + producto.getNombre() + ":");
				new Visual().mostrarRespuestaIA(descripcion);

			} catch (IOException e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
	}

	// Método para que la IA sugiera una categoría de producto
	public void sugerirCategoriaIA(Llmservice llamadaIA) {
		while (true) {
			System.out.println("\n ASISTENTE DE CATEGORIZACIÓN CON IA ");
			System.out.println("1. Sugerir categoría para nuevo producto");
			System.out.println("2. Volver al menú principal");

			int opcion = Utilidades.pedirEntero("\nSeleccione: ");

			if (opcion == 2)
				return;

			if (opcion != 1) {
				System.out.println(" Opción no válida");
				continue;
			}

			// Pedir nombre del producto
			String nombreProducto = Utilidades.pedirString("Introduce el nombre del nuevo producto: ");
			String categorias = "Figura, Manga, Póster, Llavero, Ropa, Videojuego, Otro";

			// Crear pregunta para la IA
			String prompt = "Para un producto otaku llamado '" + nombreProducto + "', "
					+ "sugiere una categoría adecuada de esta lista: " + categorias + ". "
					+ "Solo proporciona el nombre de la categoría sugerida, sin texto adicional.";

			try {
				System.out.println("\n Analizando categorías...");
				String respuesta = llamadaIA.enviarPregunta(prompt);
				System.out.println("\n Categoría sugerida para '" + nombreProducto + "':");
				new Visual().mostrarRespuestaIA(respuesta);
			} catch (IOException e) {
				System.err.println(" Error: " + e.getMessage());
			}
		}
	}
}
