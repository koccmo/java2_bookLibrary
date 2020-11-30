package internet_store.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TelegramChatId {
    private Long chatId;
    private Integer orderNumber;
}