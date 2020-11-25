package internet_store.core.service.telegram.check_telegram_chatid_service;

import internet_store.core.request.telegram.telegram_check_chatid.CheckTelegramChatIdRequest;
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