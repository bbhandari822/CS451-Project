package io.github.cs451.ge.configuration;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ConfigurationLoader {
    private final static Gson gson = new Gson();

    public static Configuration loadConfiguration() {
        try {
            //gives back configuration class
            //read the json file and map it to an actual class.
            return gson.fromJson(new FileReader(new File("config.json")), Configuration.class);
        } catch (Exception e) {
            return null;
        }
    }
}
