package io.github.cs451.ge;

import com.google.gson.Gson;
import io.github.cs451.ge.configuration.ConfigurationFile;
import io.github.cs451.ge.engine.PlayerRegistry;
import io.github.cs451.ge.service.telegram.TelegramServiceProvider;
import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class GameEngine {
    public final static Gson GSON = new Gson();
    @Getter
    private final PlayerRegistry registry;
    @Getter
    private ConfigurationFile configurationFile;

    public GameEngine() {
        this.setupConfiguration();
        new TelegramServiceProvider(this);
        registry = new PlayerRegistry(this);
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
