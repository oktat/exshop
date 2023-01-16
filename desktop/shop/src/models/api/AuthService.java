package models.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.ConfigHandler;

public class AuthService {

    ConfigHandler conf;
    public AuthService() {
        conf = new ConfigHandler("Shop.properties");
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
