package ru.taksebe.telegram.gags;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

import java.io.IOException;
import java.util.Random;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GagsBot extends SpringWebhookBot {
    String botPath;
    String botUsername;
    String botToken;

    String[] gags;
    String wtfText;

    public GagsBot(SetWebhook setWebhook) {
        super(setWebhook);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        try {
            return handleUpdate(update);
        } catch (Exception e) {
            return new SendMessage(update.getMessage().getChatId().toString(), this.wtfText);
        }
    }

    private BotApiMethod<?> handleUpdate(Update update) {
        if (update.hasCallbackQuery()) {
            return null;
        } else {
            Message message = update.getMessage();
            if (message != null) {
                return new SendMessage(message.getChatId().toString(), this.gags[new Random().nextInt(gags.length - 1)]);
            }
            return null;
        }
    }
}