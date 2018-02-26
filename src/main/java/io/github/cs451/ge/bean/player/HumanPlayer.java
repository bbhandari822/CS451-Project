package io.github.cs451.ge.bean.player;

import io.github.cs451.ge.bean.service.ServicePlayer;
import io.github.cs451.ge.bean.service.ServiceProvider;
import lombok.Getter;

import java.util.HashMap;
import java.util.UUID;

public class HumanPlayer implements Player {
    @Getter
    private final UUID id;
    private final HashMap<ServiceProvider, ServicePlayer> registeredServices;
    private String playerName;

    public HumanPlayer(UUID id) {
        this.id = id;
        this.registeredServices = new HashMap<>();
    }

    @Override
    public String getName() {
        return null;
    }

    public void addIntegration(ServiceProvider serviceProvider, ServicePlayer servicePlayer) {
        this.registeredServices.put(serviceProvider, servicePlayer);
    }
}
