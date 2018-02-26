package io.github.cs451.ge.engine;

import io.github.cs451.ge.GameEngine;
import io.github.cs451.ge.bean.player.HumanPlayer;
import io.github.cs451.ge.bean.service.ServicePlayer;
import io.github.cs451.ge.bean.service.ServiceProvider;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.UUID;

@RequiredArgsConstructor
public class PlayerRegistry {
    private final GameEngine instance;
    private final HashMap<ServicePlayer, HumanPlayer> reg;

    public HumanPlayer registerPlayer(ServiceProvider serviceProvider, ServicePlayer servicePlayer) {
        HumanPlayer humanPlayer = new HumanPlayer(UUID.randomUUID());
        humanPlayer.addIntegration(serviceProvider, servicePlayer);

        return humanPlayer;
    }
}
