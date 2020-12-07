package internet_store.integration.telegram;

import internet_store.ProductListApplication;
import internet_store.core.domain.Order;
import internet_store.core.domain.TelegramChatId;
import internet_store.core.request.ordering.FindByOrderNumberRequest;
import internet_store.core.request.telegram.CheckTelegramChatIdRequest;
import internet_store.core.service.ordering.FindByOrderNumberService;
import internet_store.core.service.telegram.CheckTelegramChatIdService;
import internet_store.database.telegram_database.InnerTelegramDatabase;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ChatBot extends TelegramLongPollingBot {
    private long chatId;
    private String chatMessage;
    private int orderNumber;

    InnerTelegramDatabase telegramDatabase = ProductListApplication.context.getBean(InnerTelegramDatabase.class);
    FindByOrderNumberService findByOrderNumberService= ProductListApplication.context.getBean(FindByOrderNumberService.class);
    CheckTelegramChatIdService checkTelegramChatIdService = ProductListApplication.context.getBean(CheckTelegramChatIdService.class);

    @Override
    public String getBotUsername() {
        return "EStoreBot";
    }

    @Override
    public String getBotToken() {
        return "1449156954:AAFiTrl8aFSUzWBgeC3twFdUHxq-JWV2emA";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            chatMessage = update.getMessage().getText();
            chatId = update.getMessage().getChatId();

            if (chatMessage.equals("/start")) {
                printStartMessage();
            }
            Order clientOrder = tryFindOrder();
            if (clientOrder != null) {
                printOrder(clientOrder);
            } else {
                printNotFoundOrder();
            }
            if (!(isChatId())) {
                telegramDatabase.addClientChatId(new TelegramChatId(chatId, orderNumber));
            }
        }
    }

    @SneakyThrows(TelegramApiException.class)
    private void printStartMessage() {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text("Enter order number for information")
                .build();
        this.execute(sendMessage);
    }

    private Order tryFindOrder() {
        orderNumber = Integer.parseInt(chatMessage);
        FindByOrderNumberRequest request = new FindByOrderNumberRequest(orderNumber);
        return findByOrderNumberService.execute(request);
    }

    @SneakyThrows(TelegramApiException.class)
    private void printOrder(Order clientOrder) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text("Information about order number: " + clientOrder.getOrderNumber()
                        + "\n" + "Order date: " + clientOrder.getOrderDate() + "\n"
                        + "Total sum: " + clientOrder.getTotalSum() + "\n"
                        + "Order status: " + clientOrder.getOrderStatus().toString())
                .build();
        this.execute(sendMessage);
    }

    @SneakyThrows(TelegramApiException.class)
    private void printNotFoundOrder() {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text("Order not found")
                .build();
        this.execute(sendMessage);
    }

    private boolean isChatId() {
        CheckTelegramChatIdRequest request = new CheckTelegramChatIdRequest(chatId);
        return checkTelegramChatIdService.execute(request);
    }
}