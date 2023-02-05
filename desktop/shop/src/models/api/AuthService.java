package models.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.ConfigHandler;
import models.HttpClient;

public class AuthService {
    String host = "http://localhost:8000/api/";
    ConfigHandler conf;
    public AuthService() {
        conf = new ConfigHandler("Shop.properties");
    }

    public AuthResponse login(String user, String pass) {
        AuthResponse authResponse = null;
        try {
            authResponse = tryLogin(user, pass);
        }catch(MalformedURLException e){
            System.err.println("Hiba! Az URL nem megfelelő!");
        }catch (IOException e) {
            System.err.println("Hiba! A HTTP kérés sikertelen!");
        }
        return authResponse;
    }
    public AuthResponse tryLogin(String user, String pass) 
            throws MalformedURLException, IOException {
        String endpoint = "signin";
        String urlStr = this.host + endpoint;
        URL url = new URL(urlStr);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();        
        
        http.setRequestMethod("POST");
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("Accept", "application/json");
        
        http.setDoOutput(true);
        String jsonInputString = "{ \"name\": \"" + 
        user + "\", \"password\": \"" + pass + "\"}";

        byte[] input = jsonInputString.getBytes();
        OutputStream os = http.getOutputStream();
        os.write(input, 0, input.length);
        os.close();

        int responseCode = http.getResponseCode();
        String text = new String();
        AuthResponse authResponse = new AuthResponse();
        if(responseCode == 200) {
            InputStream inputStream = http.getInputStream();
            text = this.convertInputStreamToString(inputStream);
            authResponse = this.getResponseObject(text);
        }else {
            System.err.println("Hiba! A HTTP lekérdezés sikertelen!");
            authResponse.setSuccess(false);
        }
        return authResponse;
    }

    public void signup(String user, String email, String pass) {
        String endpoint = "signup";
        String urlStr = this.host + endpoint;

        User userdata = new User(user, email, pass);
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String jsonInputString = gson.toJson(userdata);

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");        
        
        HttpClient httpClient = new HttpClient();
        String res = httpClient.post(urlStr, jsonInputString, headers);
        int responseCode = httpClient.getResponseCode();
        System.out.println("V: " + responseCode); //201 kód: created
        System.out.println(res);        
    }

    public String convertInputStreamToString(InputStream inputStream) {
        String responseString = null;
        try {
            responseString = this.tryConvertInputStreamToString(inputStream);
        } catch (UnsupportedEncodingException e) {
            System.err.println("Hiba! A karakterkódolás érvénytelen!");
        }
        return responseString;
    }
    public String tryConvertInputStreamToString(InputStream inputStream) 
            throws UnsupportedEncodingException {
        StringBuilder text = new StringBuilder();
        Reader reader = new   InputStreamReader(inputStream, "UTF-8");
        Scanner scanner = new Scanner(reader);
        while(scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        scanner.close();
        return text.toString();
    }

    public AuthResponse getResponseObject(String stringResponse) {        
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        AuthResponse authResponse = gson.fromJson(stringResponse, AuthResponse.class);        
        return authResponse;
    }
}
