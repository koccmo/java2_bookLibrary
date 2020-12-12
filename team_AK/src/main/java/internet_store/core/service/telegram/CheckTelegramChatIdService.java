package internet_store.core.service.telegram;

import internet_store.core.request.telegram.CheckTelegramChatIdRequest;
import internet_store.database.telegram_database.InnerTelegramDatabase;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CheckTelegramChatIdService {
    @Autowired
    InnerTelegramDatabase telegramDatabase;

    public boolean execute(CheckTelegramChatIdRequest checkTelegramChatIdRequest) {
        return telegramDatabase.isChatIdExist(checkTelegramChatIdRequest.getChatId());
    }
}