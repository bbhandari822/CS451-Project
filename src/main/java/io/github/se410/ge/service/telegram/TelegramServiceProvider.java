package io.github.se410.ge.service.telegram;

import io.github.se410.ge.bean.service.ServiceProvider;

import java.util.UUID;

public class TelegramServiceProvider implements ServiceProvider {
    @Override
    public UUID getUUID() {
        return UUID.fromString("b75c678c-6b9e-43de-ac36-87a1184d8b08");
    }

    @Override
    public String getName() {
        return "Telegram";
    }
}
