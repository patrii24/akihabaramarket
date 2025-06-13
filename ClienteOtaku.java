package akihabaramarcket;

import java.sql.Date;
import java.time.LocalDate;

//Clase que representa a un cliente otaku
public class ClienteOtaku {
	protected int id_cliente; // ID del cliente
	protected String nombre; // Nombre del cliente
	protected String email; // Correo electrónico
	protected String telefono; // Número de teléfono
	protected Date fecha_regi; // Fecha de registro

	// Métodos getter y setter (accesar o modificar valores)
	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFecha_regi() {
		return fecha_regi;
	}

	public void setFecha_regi(Date fecha_regi) {
		this.fecha_regi = fecha_regi;
	}

	// Constructor con todos los datos, incluyendo ID
	public ClienteOtaku(int id_cliente, String nombre, String email, String telefono, Date fecha_regi) {
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.fecha_regi = fecha_regi;
	}

	// Constructor sin ID (por ejemplo, para un nuevo cliente que aún no tiene ID
	// asignado)
	public ClienteOtaku(String nombre, String email, String telefono, Date fecha_regi) {
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.fecha_regi = fecha_regi;
	}
}
