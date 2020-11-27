package internet_store.core.request.telegram.telegram_chatid;

import lombok.Getter;

public class FindTelegramChatIdRequest {
    @Getter
    private final int orderNumber;

    public FindTelegramChatIdRequest(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}