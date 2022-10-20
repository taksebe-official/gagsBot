package ru.taksebe.telegram.gags.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import ru.taksebe.telegram.gags.GagsBot;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

@Configuration
@AllArgsConstructor
public class SpringConfig {
    private final TelegramConfig telegramConfig;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(telegramConfig.getWebhookPath()).build();
    }

    @Bean
    public GagsBot springWebhookBot(SetWebhook setWebhook) {
        GagsBot bot = new GagsBot(setWebhook);

        bot.setBotPath(telegramConfig.getWebhookPath());
        bot.setBotUsername(telegramConfig.getBotName());
        bot.setBotToken(telegramConfig.getBotToken());

        bot.setWtfText(telegramConfig.getWtfText());

        bot.setGags(readFileAsString().split("linelinelineline"));

        return bot;
    }

    private String readFileAsString() {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("gags.txt");

        try (Reader reader = new InputStreamReader(resource.getInputStream(), Charset.forName("Cp1251"))) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}