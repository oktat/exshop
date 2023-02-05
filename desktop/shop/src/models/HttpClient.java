package models;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HttpClient {
    int responseCode;

    public HttpClient() {
    }
    public String post(String urlStr, String data, HashMap<String, String> headers) {
        String text;
        try {
            text = tryPost(urlStr, data, headers);
        } catch (IOException e) {
            System.err.println("Hiba! A HTTP lekérdezés sikertelen!");
            text = "Hiba! A HTTP lekérdezés sikertelen!";
        }
        return text;
    }
    public String tryPost(String urlStr, String data, HashMap<String, String> headers) 
            throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        
        for(Map.Entry<String, String> entry : headers.entrySet()) {
            http.setRequestProperty(entry.getKey(), entry.getValue());
        }

        http.setDoOutput(true);
        byte[] inputArray = data.getBytes();
        OutputStream outputStream = http.getOutputStream();
        outputStream.write(inputArray);
        outputStream.close();
        
        this.responseCode = http.getResponseCode();        
        InputStream inputStream = http.getInputStream();
        String text = this.convertInputStreamToString(inputStream);        
        return text;
    }
    public int getResponseCode() {
        return this.responseCode;
    }
    private String convertInputStreamToString(InputStream inputStream) {
        String responseString = null;
        try {
            responseString = this.tryConvertInputStreamToString(inputStream);
        } catch (UnsupportedEncodingException e) {
            System.err.println("Hiba! A karakterkódolás érvénytelen!");
        }
        return responseString;
    }
    private String tryConvertInputStreamToString(InputStream inputStream) 
            throws UnsupportedEncodingException {
        StringBuilder text = new StringBuilder();
        Reader reader = new   java.io.InputStreamReader(inputStream, "UTF-8");
        Scanner scanner = new Scanner(reader);
        while(scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        scanner.close();
        return text.toString();
    }        
}
