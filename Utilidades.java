package akihabaramarcket;
import java.util.Scanner;
import java.util.regex.Pattern;
public class Utilidades {
	// funcion para pedir enteros
	 public static int pedirEntero(String mensaje) {
	        Scanner scan = new Scanner(System.in);
	        int valor = 0;
	        boolean error = false;
	        do {
	            error = false;
	            System.out.print(mensaje);
	            try {
	                valor = Integer.parseInt(scan.next());
	            } catch (Exception e) {
	                System.out.println("[ERROR] Valor incorrecto");
	                error = true;
	            }
	        } while (error);
	        return valor;

	    }
// funcion para pedir string
	    public static String pedirString(String mensaje) {
	        Scanner scan = new Scanner(System.in);
	        String valor="" ;
	        boolean error = false;
	        do {
	            error = false;
	            System.out.print(mensaje);
	            try {
	                valor = scan.nextLine();
	            } catch (Exception e) {
	                System.out.println("Valor incorrecto");
	                error = true;
	            }
	        } while (error);
	        return valor;
	    }
	    // funcion para pedir booleanos
	    public static boolean pedirBoolean(String mensaje) {
	        Scanner scan = new Scanner(System.in);
	        boolean valor = false;
	        boolean error;

	        do {
	            error = false;
	            System.out.print(mensaje + " (true/false): ");
	            String entrada = scan.nextLine().trim().toLowerCase();

	            if (entrada.equals("true")) {
	                valor = true;
	            } else if (entrada.equals("false")) {
	                valor = false;
	            } else {
	                System.out.println(" Solo se permite 'true' o 'false'.");
	                error = true;
	            }
	        } while (error);

	        return valor;
	    }
	    // y funcion para pedir numeros decimales
	    public static double pedirdouble(String mensaje) {
	        Scanner scan = new Scanner(System.in);
	        double valor = 0.0;
	        boolean error;
	        do {
	            error = false;
	            System.out.print(mensaje);
	            try {
	                valor = Double.parseDouble(scan.next());
	            } catch (Exception e) {
	                System.out.println("[ERROR] Valor incorrecto");
	                error = true;
	            }
	        } while (error);
	        return valor;

	    }
	 // función para pedir un correo electrónico válido
	    public static String pedirEmail(String mensaje) {
	        Scanner scan = new Scanner(System.in);
	        String email;
	        boolean error;
	        do {
	            error = false;
	            System.out.print(mensaje);
	            email = scan.nextLine();
	            if (!Pattern.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", email)) {
	                System.out.println("[ERROR] Correo inválido. Intente de nuevo.");
	                error = true;
	            }
	        } while (error);
	        return email;
	    }
	 // función para pedir un número de teléfono válido (solo dígitos y 9-12 caracteres) 
	    public static String pedirTelefono(String mensaje) {
	        Scanner scan = new Scanner(System.in);
	        String telefono;
	        boolean error;
	        do {
	            error = false;
	            System.out.print(mensaje);
	            telefono = scan.nextLine();
	            if (!Pattern.matches("^\\d{9,12}$", telefono)) {
	                System.out.println("[ERROR] Teléfono inválido. Debe contener entre 9 y 12 dígitos.");
	                error = true;
	            }
	        } while (error);
	        return telefono;
	    }

	}




