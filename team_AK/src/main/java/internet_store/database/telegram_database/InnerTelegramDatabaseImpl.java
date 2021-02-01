package internet_store.database.telegram_database;

import internet_store.core.domain.TelegramChatId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InnerTelegramDatabaseImpl implements InnerTelegramDatabase {
    final List<TelegramChatId> allChatId = new ArrayList<>();

    @Override
    public void addClientChatId(TelegramChatId telegramChatId) {
        allChatId.add(telegramChatId);
    }

    @Override
    public List<TelegramChatId> findByOrderNumber(int orderNumber) {
        return allChatId.stream().filter(pr -> pr.getOrderNumber() == orderNumber)
                .collect(Collectors.toList());
    }

    public boolean isChatIdExist(long chatId) {
        return allChatId.stream().anyMatch(pr -> pr.getChatId() == chatId);
    }
}