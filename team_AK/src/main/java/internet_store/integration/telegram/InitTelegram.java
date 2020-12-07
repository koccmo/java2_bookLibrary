package internet_store.integration.telegram;

import dependency.annotation.DIComponent;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@DIComponent
public class InitTelegram {
    private ChatBot chatBot;

    @SneakyThrows(TelegramApiException.class)
    public void init() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            chatBot = new ChatBot();
            telegramBotsApi.registerBot(chatBot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public ChatBot getChatBot() {
        return chatBot;
    }
}