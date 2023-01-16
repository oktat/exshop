package models.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.ConfigHandler;
import models.DataService;
import models.Product;

public class DataServiceApi implements DataService {
    ConfigHandler conf;
    public DataServiceApi() {
        conf = new ConfigHandler("Shop.properties");
    }

    public CompletableFuture<String> getProductsJson() {
        String host = conf.getProperty("api.host");
        String endpoint = "products";
        String url = host + endpoint;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build();

        return client.sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpResponse::body);
    }

    @Override
    public ArrayList<Product> getProducts() {
        String jsonContent = this.getProductsJson().join();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
 
        Product[] employeeArray = gson.fromJson(jsonContent, Product[].class);
        ArrayList<Product> productList = new ArrayList<>(Arrays.asList(employeeArray));        
        return productList;
    }
        
}
