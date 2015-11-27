package com.comeb;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FetchURLServlet extends HttpServlet {
	private static final String URL_TO_FETCH = "http://localhost/service/index.php";

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			URL url = new URL(URL_TO_FETCH);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("message=Hello");
			writer.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			StringBuilder sb = new StringBuilder();
			String output;
			while ((output = reader.readLine()) != null) {
				sb.append(output);
			}
			String mes=sb.toString();

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				resp.getWriter().write("resp:"+mes);

				// Connexion réussie, possibilité de récupérer le flux de retour
			} else {
				// HTTP error code.
			}
		} catch (MalformedURLException e) {
			// Gestion d’exceptions d’ouverture de flux
			e.printStackTrace();

		} catch (IOException e) {
			// Gestion d’exceptions de lecture de flux
			e.printStackTrace();
		}
	}

}
