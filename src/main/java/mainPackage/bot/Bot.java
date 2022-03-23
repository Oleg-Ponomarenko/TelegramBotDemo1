package mainPackage.bot;

import mainPackage.storage.Storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Configuration
@EnableScheduling
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Value("${bot.myChatId}")
    private int myChatId;

    Storage storage;

    Bot() {
        this.storage = new Storage();
    }

    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
    }

    @Scheduled(fixedDelay = 1000)
    public void sendRates() {
        try {
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(myChatId));
            message.setText("Доллар США: " + storage.getRate("USD")
                    + "\n\nКитайский юань: " + storage.getRate("CNY")
                    + "\n\nФунт стерлингов: " + storage.getRate("GBR")
                    + "\n\nЯпонский иен: " + storage.getRate("JPY"));
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

    }
}
