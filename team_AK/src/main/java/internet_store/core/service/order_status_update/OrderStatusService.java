package internet_store.core.service.order_status_update;

import internet_store.ProductListApplication;
import internet_store.core.domain.Order;
import internet_store.core.domain.TelegramChatId;
import internet_store.core.request.order_status_update.OrderStatusRequest;
import internet_store.core.request.telegram.telegram_chatid.FindTelegramChatIdRequest;
import internet_store.database.order_database.InnerOrderDatabase;
import internet_store.integration.telegram.ChatBot;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class OrderStatusService {
    private final InnerOrderDatabase orderDatabase;
    private Order order;

    public OrderStatusService(InnerOrderDatabase orderDatabase) {
        this.orderDatabase = orderDatabase;
    }

    public void execute(OrderStatusRequest orderStatusRequest) {
        long orderId = orderStatusRequest.getOrderId();

        order = orderDatabase.findById(orderId);

        order.setOrderStatus(orderStatusRequest.getOrderStatus());

        List<TelegramChatId> clientChatId = tryFindClientChatId();
        clientChatId.forEach(this::printNewInformation);
    }

    private List<TelegramChatId> tryFindClientChatId() {
        FindTelegramChatIdRequest request = new FindTelegramChatIdRequest(order.getOrderNumber());
        return ProductListApplication.telegramChatIdService.execute(request);
    }

    @SneakyThrows(TelegramApiException.class)
    private void printNewInformation(TelegramChatId clientChatId) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(String.valueOf(clientChatId.getChatId()))
                .text("New information about order number: " + order.getOrderNumber()
                        + "\n" + "Order date: " + order.getOrderDate() + "\n"
                        + "Total sum: " + order.getTotalSum() + "\n"
                        + "Order status: " + order.getOrderStatus().toString())
                .build();
        ChatBot chatBot = ProductListApplication.initTelegram.getChatBot();
        chatBot.execute(sendMessage);
    }
}