package internet_store.core.service.telegram;

import internet_store.core.request.telegram.CheckTelegramChatIdRequest;
import internet_store.database.telegram_database.InnerTelegramDatabase;
import dependency.annotation.DIComponent;
import dependency.annotation.DIDependency;

@DIComponent
public class CheckTelegramChatIdService {
    @DIDependency
    InnerTelegramDatabase telegramDatabase;

    public boolean execute(CheckTelegramChatIdRequest checkTelegramChatIdRequest) {
        return telegramDatabase.isChatIdExist(checkTelegramChatIdRequest.getChatId());
    }
}