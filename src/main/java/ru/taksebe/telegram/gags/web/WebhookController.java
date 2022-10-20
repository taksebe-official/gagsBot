package ru.taksebe.telegram.gags.web;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.taksebe.telegram.gags.GagsBot;

@RestController
@AllArgsConstructor
public class WebhookController {
    private final GagsBot gagsBot;

    @PostMapping("/gags")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return gagsBot.onWebhookUpdateReceived(update);
    }
}