package internet_store.core.request.telegram;

import lombok.Getter;

public class CheckTelegramChatIdRequest {
    @Getter
    private final long chatId;

    public CheckTelegramChatIdRequest(long chatId) {
        this.chatId = chatId;
    }
}