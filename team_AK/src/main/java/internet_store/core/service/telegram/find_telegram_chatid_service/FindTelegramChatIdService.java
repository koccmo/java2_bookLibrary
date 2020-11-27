package internet_store.core.service.telegram.find_telegram_chatid_service;

import internet_store.core.domain.TelegramChatId;
import internet_store.core.request.telegram.telegram_chatid.FindTelegramChatIdRequest;
import internet_store.database.telegram_database.InnerTelegramDatabase;

import java.util.List;

public class FindTelegramChatIdService {
    private final InnerTelegramDatabase telegramDatabase;

    public FindTelegramChatIdService(InnerTelegramDatabase telegramDatabase) {
        this.telegramDatabase = telegramDatabase;
    }

    public List<TelegramChatId> execute(FindTelegramChatIdRequest findTelegramChatIdRequest) {
        return telegramDatabase.findByOrderNumber(findTelegramChatIdRequest.getOrderNumber());
    }
}