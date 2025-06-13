
#  Akihabara Market

**Autor**: Patricia Rodríguez Arroyo  
**Fecha**: 13 de Junio de 2025  
**Versión**: 1.0  

---

##  Índice
1. Introducción  
2. Requisitos Previos  
3. Estructura del Proyecto  
4. Descripción General del Producto  
5. Instrucciones de Uso  
6. Configuración de Base de Datos (MySQL)  
7. Configuración de API Key para OpenRouter  
8. Compilación y Ejecución  
9. Estructura de Clases  

---

## 1. Introducción
**Akihabara Market** es una plataforma de escritorio desarrollada en Java que permite gestionar productos relacionados con la cultura japonesa: anime, manga, figuras coleccionables, entre otros.  
Este manual está dirigido a usuarios finales y desarrolladores que deseen utilizar o ampliar el sistema.

---

## 2. Requisitos Previos
- Eclipse IDE (o cualquier entorno Java compatible)  
- Java JDK 8 o superior  
- MySQL instalado y corriendo  
- Conexión a Internet  
- Acceso a una clave de API de [OpenRouter.ai](https://openrouter.ai/) para funciones asistidas por IA

---

## 3. Estructura del Proyecto

AkihabaraMarket/<br>
│<br>
├── akihabaramarcket/<br>
│   ├── Akihabara.java         ← Clase principal (Main)<br>
│   ├── Visual.java            ← Clase para mostrar menús y datos<br>
│   ├── Productos.java         ← Modelo de datos de producto<br>
│   ├── ClienteOtaku.java      ← Modelo de cliente<br>
│   ├── ProductosDAO.java      ← Acceso a BD para productos<br>
│   ├── ClienteOtakuDAO.java   ← Acceso a BD para clientes<br>
│   ├── OpenRouter.java        ← Controlador de IA<br>
│   └── ...otros archivos<br>
│<br>
├── resources/<br>
│   └── config.properties      ← Donde se configura la API Key<br>
│<br>
├── database/<br>
│   └── schema.sql             ← Script SQL para la BD<br>
│<br>
└── README.md                  ← Este manual<br>

---

## 4. Descripción General del Producto

### Secciones principales:
- **Añadir Productos**: Agrega nuevos artículos al sistema.  
- **Buscar Productos por ID o Nombre**: Encuentra productos mediante su identificador o nombre.  
- **Mostrar Lista de Productos**: Visualiza todos los productos en la base de datos.  
- **Actualizar Productos**: Modifica información existente.  
- **Eliminar Productos**: Elimina productos usando su ID.  
- **Gestión de Clientes**: CRUD completo para clientes.  
- **Asistente IA**: Brinda respuestas, genera descripciones o categorías para productos.  

---

## 5. Instrucciones de Uso

### Iniciar el programa
1. Abre Eclipse e importa el proyecto.
2. Ejecuta la clase `Akihabara.java`.
3. Asegúrate de que la base de datos esté conectada y accesible.

---

### Funciones disponibles

####  Añadir Productos
1. Selecciona la opción `1. AGREGAR PRODUCTO`.
2. Rellena los campos requeridos.
3. El producto se añadirá y volverás al menú.

####  Buscar Producto por ID o Nombre
- ID: opción `2`.  
- Nombre: opción `6`.  
El producto aparecerá si existe.

####  Mostrar Lista de Productos
- Opción `3`.  
- Se imprimirá en tabla la lista actual.

####  Actualizar Producto
- Opción `4`.  
- Introduce el ID y selecciona qué campo cambiar.

####  Eliminar Producto
- Opción `5`.  
- Introduce el ID del producto. Se confirmará su eliminación.

####  Asistente IA
- Opción `7`: Preguntas generales.
- Opción `8`: Generar descripción automática.
- Opción `9`: Asignar categoría automáticamente.

---
## Funcionalidades para Clientes

Además de la gestión de productos, **Akihabara Market** permite administrar clientes que forman parte de la base de datos. A continuación se detallan las operaciones disponibles:

### Añadir Cliente
1. Selecciona la opción **Agregar Cliente** en el menú de clientes.
2. Introduce los datos solicitados: nombre, email, número de teléfono y fecha de registro.
3. El cliente será guardado en la base de datos y volverás al menú.

### Obtener Cliente por ID
1. Selecciona **Obtener cliente por ID**.
2. Introduce el ID único del cliente.
3. Se mostrará en pantalla la información correspondiente a ese cliente.

### Mostrar Lista de Clientes
1. Selecciona la opción **Mostrar clientes**.
2. Se imprimirá por pantalla una tabla con todos los clientes registrados.

### Actualizar Cliente
1. Selecciona **Actualizar cliente**.
2. Introduce el ID del cliente que deseas actualizar.
3. Elige el campo que quieres modificar: nombre, email, teléfono o fecha de registro.
4. Introduce el nuevo valor y se actualizará la información en la base de datos.

### Eliminar Cliente
1. Selecciona **Eliminar cliente**.
2. Introduce el ID del cliente que deseas borrar.
3. Si el cliente existe, será eliminado de la base de datos.

### Buscar Cliente por Email
1. Selecciona **Buscar cliente por email**.
2. Introduce el correo electrónico del cliente.
3. Se mostrará la información si el cliente está registrado.

Estas funcionalidades están implementadas principalmente en las clases `ClienteOtaku`, `ClienteOtakuDAO` y `Visual`, siguiendo el patrón de arquitectura Modelo-Vista-Controlador (MVC).

---

## 6. Configuración de Base de Datos MySQL

### Crear base de datos y tablas
1. Abre tu cliente de MySQL.
2. Ejecuta el archivo `database/schema.sql` que contiene las tablas necesarias:
```sql
CREATE DATABASE akihabara;
USE akihabara;

CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    categoria VARCHAR(100),
    precio DOUBLE,
    stock INT
);

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    email VARCHAR(100),
    telefono VARCHAR(20),
    fecha_registro DATE
);
```

### Conexión desde Java
En tu clase DAO asegúrate de tener la siguiente cadena:
```java
String url = "jdbc:mysql://localhost:3306/akihabara";
String user = "root";
String password = "tu_contraseña";
```

---

## 7. Configuración de API Key para OpenRouter

1. Regístrate en [https://openrouter.ai](https://openrouter.ai).
2. Copia tu API Key.
3. En el archivo `resources/config.properties` añade esta línea:
```
openrouter.apikey=TU_API_KEY_AQUI
```
4. En la clase `OpenRouter.java`, asegúrate de que el código lea este archivo.

---

## 8. Compilación y Ejecución

### Desde Eclipse:
- Click derecho en `Akihabara.java` → `Run As` → `Java Application`

### Desde consola:
```bash
javac akihabaramarcket/*.java
java akihabaramarcket.Akihabara
```

---

## 9. Estructura de Clases
El proyecto **Akihabara Market** está organizado en diferentes clases, cada una con una función clara dentro del sistema:

La clase **`Akihabara`** actúa como controlador principal del programa. Es el punto de entrada desde el cual se inicializa el menú y se gestiona la interacción del usuario con las distintas funcionalidades del sistema.

La clase **`Visual`** es responsable de mostrar por consola todos los menús, resultados, listas de productos o clientes, y también las respuestas del asistente de IA. Se encarga de toda la parte visual de la aplicación en modo texto.

La clase **`Productos`** representa la entidad producto, incluyendo atributos como nombre, categoría, precio y stock. Es un modelo que sirve para manipular objetos de tipo producto de forma estructurada.

La clase **`ClienteOtaku`** es el modelo que representa a un cliente, incluyendo información como nombre, correo electrónico, teléfono y fecha de registro. Al igual que la clase `Productos`, actúa como contenedor de datos.

La clase **`ProductosDAO`** gestiona todas las operaciones relacionadas con los productos en la base de datos, como añadir, buscar, listar, actualizar y eliminar productos. Utiliza JDBC para conectarse a MySQL.

De forma similar, la clase **`ClienteOtakuDAO`** se encarga de la gestión de los clientes, con operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los registros de clientes en la base de datos.

Por último, la clase **`OpenRouter`** permite la comunicación con una inteligencia artificial a través de una API. Esta clase se utiliza para generar descripciones y categorías automáticas de los productos usando lenguaje natural.

---

##ENLACE GITHUB
<a>https://github.com/patrii24/akihabaramarket.git<a>
