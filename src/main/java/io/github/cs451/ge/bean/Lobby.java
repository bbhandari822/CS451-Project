package io.github.cs451.ge.bean;

import com.jtelegram.api.util.TextBuilder;
import io.github.cs451.ge.bean.player.Player;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.LinkedList;

@RequiredArgsConstructor
public class Lobby<T extends Game> {
    private final T game;
    private final int size;
    private final LinkedList<Player> players = new LinkedList<>();
    private final HashSet<Player> set = new HashSet<>();

    public TextBuilder getLobbyText() {
        Player player = getFirstPlayer();
        String waitingMessage = "Empty lobby - press join to join it.";
        if (player != null && size != 0) {
            waitingMessage = String.format("%s is waiting, join the lobby", player.getName());
        }

        if (isFull()) {
            waitingMessage = String.format("Play the game...");
        }

        return TextBuilder.create()
                .bold(game.getName()).newLine()
                .plain(waitingMessage);

    }

    public Player getFirstPlayer() {
        try {
            return players.getFirst();
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean isFull() {
        return players.size() >= size;
    }

    public boolean joinPlayer(Player player) {
        if (isFull() || contains(player)) return false;

        players.add(player);
        set.add(player);
        return true;
    }

    public boolean leavePlayer(Player player) {
        if (!contains(player)) {
            return false;
        }

        players.remove(player);
        set.remove(player);
        return true;
    }

    public boolean contains(Player player) {
        return set.contains(player);
    }
}
