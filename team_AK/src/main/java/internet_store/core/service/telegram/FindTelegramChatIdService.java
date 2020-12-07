package internet_store.core.service.telegram;

import internet_store.core.domain.TelegramChatId;
import internet_store.core.request.telegram.FindTelegramChatIdRequest;
import internet_store.database.telegram_database.InnerTelegramDatabase;
import dependency.annotation.DIComponent;
import dependency.annotation.DIDependency;

import java.util.List;
@DIComponent
public class FindTelegramChatIdService {
    @DIDependency
    InnerTelegramDatabase telegramDatabase;

    public List<TelegramChatId> execute(FindTelegramChatIdRequest findTelegramChatIdRequest) {
        return telegramDatabase.findByOrderNumber(findTelegramChatIdRequest.getOrderNumber());
    }
}