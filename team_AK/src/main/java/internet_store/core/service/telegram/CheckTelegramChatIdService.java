package internet_store.core.service.telegram;

import internet_store.core.request.telegram.CheckTelegramChatIdRequest;
import internet_store.database.telegram_database.InnerTelegramDatabase;

public class CheckTelegramChatIdService {
    private final InnerTelegramDatabase telegramDatabase;

    public CheckTelegramChatIdService(InnerTelegramDatabase telegramDatabase) {
        this.telegramDatabase = telegramDatabase;
    }

    public boolean execute(CheckTelegramChatIdRequest checkTelegramChatIdRequest) {
        return telegramDatabase.isChatIdExist(checkTelegramChatIdRequest.getChatId());
    }
}