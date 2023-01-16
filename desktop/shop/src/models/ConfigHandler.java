package models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHandler extends Properties {
    String filename;
    public ConfigHandler(String filename) {
        this.filename = filename;
        this.loadConfig();
    }
    public void loadConfig() {
        try {
            this.tryLoadConfig();
        } catch (FileNotFoundException e) {
            System.err.println("Hiba! A fájl nem található!");
        } catch (IOException e) {
            System.err.println("Hiba! A fájl bezárása sikertelen!");
        }
    }
    public void tryLoadConfig() throws IOException, FileNotFoundException {
        InputStream inputStream = new FileInputStream(this.filename);
        this.load(inputStream);
        inputStream.close();
    }
    
}
