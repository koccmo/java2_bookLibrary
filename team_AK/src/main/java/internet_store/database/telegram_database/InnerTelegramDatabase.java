package internet_store.database.telegram_database;

import internet_store.core.domain.TelegramChatId;

import java.util.List;

public interface InnerTelegramDatabase {
    void addClientChatId(TelegramChatId telegramChatId);

    List<TelegramChatId> findByOrderNumber(int orderNumber);

    boolean isChatIdExist(long chatId);

}