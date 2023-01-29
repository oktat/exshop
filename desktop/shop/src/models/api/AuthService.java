package models.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.ConfigHandler;

public class AuthService {

    ConfigHandler conf;
    public AuthService() {
        conf = new ConfigHandler("Shop.properties");
    }

    public String login2(String user, String pass) {
        try {
            tryLogin2(user, pass);
        }catch(MalformedURLException e){
            System.err.println("Hiba! Az URL nem megfelelő!");
        }catch (IOException e) {
            System.err.println("Hiba! A HTTP kérés sikertelen!");
        }
        return "";
    }
    public String tryLogin2(String user, String pass) 
            throws MalformedURLException, IOException {
        // String host = "http://[::1]:8000/api/";
        String host = "http://localhost:8000/api/";
        String endpoint = "signin";
        String urlStr = host + endpoint;
        System.out.println(urlStr);

        URL url = new URL(urlStr);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        
        
        http.setRequestMethod("POST");
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("Accept", "application/json");
        
        http.setDoOutput(true);
        String jsonInputString = "{ \"name\": \"mari\", \"password\": \"titok\"}";
        byte[] input = jsonInputString.getBytes();
        OutputStream os = http.getOutputStream();
        os.write(input, 0, input.length);
        os.close();

        http.connect();


        int responseCode = http.getResponseCode();
        StringBuilder text = new StringBuilder();
        if(responseCode == 200) {
            InputStream inputStream = http.getInputStream();
            Reader reader = new   InputStreamReader(inputStream, "UTF-8");
            Scanner scanner = new Scanner(reader);            
            while(scanner.hasNextLine()) {
                text.append(scanner.nextLine());
            }
            scanner.close();
        }else {
            System.err.println("Hiba! A HTTP lekérdezés sikertelen!");
        }        
        System.out.println(text.toString());

        return "";
    }

    public CompletableFuture<String> loginJson(String user, String pass) {
        String host = conf.getProperty("api.host");
        String endpoint = "signin";
        String url = host + endpoint;


        
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        var authData = new AuthData(user, pass);
        var bodyJson = gson.toJson(authData);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .header("Accept", "application/json")
        .header("Content-Type", "application/json")
        .POST(BodyPublishers.ofString(bodyJson))
        .build();

        return client.sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpResponse::body);
    }
    public AuthResponse login(String user, String pass) {
        String jsonContent = this.loginJson(user, pass).join();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
 
        AuthResponse authResponse = gson.fromJson(jsonContent, AuthResponse.class);
        
        return authResponse;
    }
}
