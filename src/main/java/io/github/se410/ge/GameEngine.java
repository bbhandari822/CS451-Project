package io.github.se410.ge;

import com.google.gson.Gson;
import io.github.se410.ge.configuration.ConfigurationFile;
import io.github.se410.ge.service.telegram.TelegramServiceProvider;
import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class GameEngine {
    public final static Gson GSON = new Gson();
    @Getter
    private ConfigurationFile configurationFile;

    public GameEngine() {
        this.setupConfiguration();
        new TelegramServiceProvider(this);
    }

    public static void main(String... args) {
        new GameEngine();
    }

    private void setupConfiguration() {
        try {
            this.configurationFile = GSON.fromJson(new FileReader("config.json"), ConfigurationFile.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
