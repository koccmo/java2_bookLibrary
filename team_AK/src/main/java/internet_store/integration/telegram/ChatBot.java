package internet_store.integration.telegram;

import internet_store.core.domain.Order;
import internet_store.integration.PrintService;
import internet_store.integration.telegram.service.AddtTelegramChatIdService;
import internet_store.integration.telegram.service.CheckTelegramChatIdService;
import internet_store.integration.telegram.service.FindOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class ChatBot extends TelegramLongPollingBot {
    @Autowired
    private FindOrderService findOrderService;
    @Autowired
    private CheckTelegramChatIdService telegramChatIdService;
    @Autowired
    private AddtTelegramChatIdService addtTelegramChatIdService;
    @Autowired
    private PrintService printService;
    @Value("${botName}")
    private String botName;
    @Value("${botToken}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        AtomicBoolean recordChatIdAndOrderNumberExists = new AtomicBoolean(false);
        if (update.hasMessage()) {
            String chatMessage = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (chatMessage.equals("/start")) {
                printService.printStartMessage(chatId);
                return;
            }

            Optional<Order> clientOrder = findOrderService.tryFindOrder(chatMessage);

            clientOrder.ifPresentOrElse(order -> {
                printService.printOrder(order, chatId);
                recordChatIdAndOrderNumberExists
                        .set(isRecordChatIdAndOrderNumber(chatId, order.getNumber()));
            }, () -> printService.printNoFoundOrder(chatId));

            if (!(recordChatIdAndOrderNumberExists).get()) {
                clientOrder.ifPresent(order -> addtTelegramChatIdService
                        .AddNewClientChatId(chatId, order.getNumber()));
            }
        }
    }

    private boolean isRecordChatIdAndOrderNumber(long chatId, String orderNumber) {
        return telegramChatIdService.isRecordExists(chatId, orderNumber);
    }
}