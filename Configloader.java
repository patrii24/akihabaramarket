package akihabaramarcket;

import java.io.InputStream;
import java.util.Properties;

public class Configloader {

	// Objeto 'Properties' que almacenará todas las claves y valores leídos del
	// archivo .properties
	private static Properties prop = new Properties();

	// Bandera para evitar que el archivo se cargue más de una vez
	private static boolean loaded = false;

	// Método privado que carga el archivo de configuración desde el classpath
	private static void loadConfig() {
		// Solo carga si no se ha cargado antes
		if (!loaded) {
			try (
					// Intenta obtener el archivo 'config.properties' desde el classpath (carpeta
					// 'resources')
					InputStream input = Configloader.class.getClassLoader().getResourceAsStream("config.properties")) {
				// Si no se encuentra el archivo, muestra un mensaje de error
				if (input == null) {
					System.err.println("Archivo properties no encontrado en el classpath");
					return;
				}

				// Carga las propiedades desde el archivo en el objeto 'prop'
				prop.load(input);

				// Marca que ya se ha cargado para que no se repita
				loaded = true;

			} catch (Exception e) {
				// Si ocurre un error, lo muestra por consola
				System.err.println("Error cargando configuración: " + e.getMessage());
			}
		}
	}

	// Método público que obtiene una propiedad por su clave
	public static String getProperty(String key) {
		// Asegura que el archivo esté cargado antes de acceder
		loadConfig();
		// Devuelve el valor asociado a la clave (o null si no existe)
		return prop.getProperty(key);
	}

	// Método público que obtiene una propiedad por su clave, o un valor por defecto
	// si no existe
	public static String getProperty(String key, String defaultValue) {
		// Asegura que el archivo esté cargado
		loadConfig();
		// Devuelve la propiedad si existe, o el valor por defecto si no
		return prop.getProperty(key, defaultValue);
	}

	// Método de prueba: imprime la ruta desde donde se está leyendo el archivo
	// 'config.properties'
	public static void main(String[] args) {
		System.out.println("Buscando config.properties...");
		// Muestra la URL donde se ha encontrado el archivo en el classpath (para
		// depuración)
		System.out.println(Configloader.class.getClassLoader().getResource("config.properties"));
	}
}
