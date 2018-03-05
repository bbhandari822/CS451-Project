package io.github.cs451.ge.bean.player;

import com.jtelegram.api.user.User;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
import java.util.UUID;

@ToString
public class HumanPlayer implements Player {
    @Getter
    private final UUID id;
    private User user;

    public HumanPlayer(UUID id) {
        this.id = id;
    }

    @Override
    public String getName() {
        if (user == null) {
            return "none specified";
        }
        return user.getUsernameFallbackName();
    }

    public void addIntegration(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanPlayer that = (HumanPlayer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
