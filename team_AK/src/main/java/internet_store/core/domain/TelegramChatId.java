package internet_store.core.domain;

import lombok.Data;

@Data
public class TelegramChatId {
    private long chatId;
    private int orderNumber;

    public TelegramChatId(long chatId, int orderNumber) {
        this.chatId = chatId;
        this.orderNumber = orderNumber;
    }
}