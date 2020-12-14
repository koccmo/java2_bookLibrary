package internet_store.core.service.telegram;

import internet_store.core.domain.TelegramChatId;
import internet_store.core.request.telegram.FindTelegramChatIdRequest;
import internet_store.database.telegram_database.InnerTelegramDatabase;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class FindTelegramChatIdService {
    @Autowired
    InnerTelegramDatabase telegramDatabase;

    public List<TelegramChatId> execute(FindTelegramChatIdRequest findTelegramChatIdRequest) {
        return telegramDatabase.findByOrderNumber(findTelegramChatIdRequest.getOrderNumber());
    }
}