package io.github.se410.ge.bean;

import java.util.HashMap;
import java.util.UUID;

public class Player {
    private final UUID playerId;
    private final HashMap<ServiceProvider, ServicePlayer> registeredServices;
    private String playerName;

    public Player(UUID playerId, HashMap<ServiceProvider, ServicePlayer> registeredServices) {
        this.playerId = playerId;
        this.registeredServices = registeredServices;
    }
}
