package internet_store.integration.telegram;

import internet_store.ProductListApplication;
import internet_store.core.domain.Order;
import internet_store.core.domain.TelegramChatId;
import internet_store.core.request.find_by_order_number.FindByOrderNumberRequest;
import internet_store.core.request.telegram.telegram_check_chatid.CheckTelegramChatIdRequest;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ChatBot extends TelegramLongPollingBot {
    private long chatId;
    private String chatMessage;
    private int orderNumber;

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
                ProductListApplication.telegramDatabase.addClientChatId(
                        new TelegramChatId(chatId, orderNumber));
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
        return ProductListApplication.findByOrderNumberService.execute(request);
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
        return ProductListApplication.checkTelegramChatIdService.execute(request);
    }
}