package akihabaramarcket;

import okhttp3.MediaType; // NO java.awt.PageAttributes.MediaType
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

public class Llmservice {

	// Clave de la API
	private static final String API_KEY = Configloader.getProperty("apikey"); // <-- OJO: esta clave es sensible
	// URL del endpoint que voy a utilizar para hacer las solicitudes a la IA
	private static final String URL = Configloader.getProperty("url.api", "https://openrouter.ai/api/v1/chat/completions");
	// Especifico que el contenido que enviaré será JSON con codificación UTF-8
	private static final MediaType JSON_MEDIA = MediaType.get("application/json; charset=utf-8");

	// Configuro el cliente HTTP con un timeout decente para conexiones y lectura
	private final OkHttpClient client = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
			.readTimeout(60, TimeUnit.SECONDS).build();

	// Este método es el que envío una pregunta (prompt) al modelo de lenguaje
	public String enviarPregunta(String prompt) throws IOException {
		// Armo la lista de mensajes que se van a enviar, tipo chat (formato esperado
		// por la API)
		JSONArray mensajes = new JSONArray();

		// Primer mensaje: le doy un contexto al modelo para que responda como experto
		// en ventas de anime
		mensajes.put(new JSONObject().put("role", "system").put("content",
				"Eres un experto en ventas de productos Akihabara Market,"
						+ " especializado en productos de anime y series,"
						+ " Responde de manera técnica pero accesible."));

		// Segundo mensaje: el que realmente quiero enviar (lo que escriba el usuario)
		mensajes.put(new JSONObject().put("role", "user").put("content", prompt));

		// Armo el cuerpo del request con modelo, mensajes, tokens máximos, etc.
		JSONObject requestBody = new JSONObject().put("model", "gpt-3.5-turbo").put("messages", mensajes)
				.put("max_tokens", 1000).put("temperature", 0.7); // Un poco de creatividad, pero sin exagerar

		// Construyo la solicitud HTTP con todos los headers necesarios
		Request request = new Request.Builder().url(URL).header("Authorization", "Bearer " +API_KEY)
				.header("Content-Type", "application/json").header("X-Title", "AkihabaraMarket")
				.header("HTTP-Referer", "https://akihabaramarket.com")
				.post(RequestBody.create(requestBody.toString(), JSON_MEDIA)).build();

		// Ejecuto la solicitud y manejo la respuesta
		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				// Si algo falla, obtengo el cuerpo del error y lanzo una excepción
				String errorBody = response.body().string();
				throw new IOException("Error en la API: " + response.code() + " - " + errorBody);
			}
			// Si todo sale bien, proceso y devuelvo la respuesta de la IA
			return procesarRespuesta(response.body().string());
		}
	}

	// Este método toma la respuesta JSON y extrae el mensaje que devuelve el modelo
	private String procesarRespuesta(String jsonResponse) {
		JSONObject json = new JSONObject(jsonResponse);
		return json.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
	}
}