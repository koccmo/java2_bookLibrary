package internet_store.core.service.ordering;

import internet_store.core.domain.Order;
import internet_store.core.domain.TelegramChatId;
import internet_store.core.request.ordering.OrderStatusRequest;
import internet_store.core.request.telegram.FindTelegramChatIdRequest;
import internet_store.core.service.telegram.FindTelegramChatIdService;
import internet_store.database.order_database.InnerOrderDatabase;
import internet_store.integration.mail.EmailServiceImpl;
import internet_store.integration.telegram.ChatBot;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class OrderStatusService {
    @Autowired
    private InnerOrderDatabase orderDatabase;
    @Autowired
    private FindTelegramChatIdService telegramChatIdService;
    @Autowired
    private ChatBot chatBot;
    @Autowired
    private EmailServiceImpl emailService;
    private Order order;

    public void execute(OrderStatusRequest orderStatusRequest) {
        long orderId = orderStatusRequest.getOrderId();

        order = orderDatabase.findById(orderId);

//        order.setOrderStatus(orderStatusRequest.getOrderStatus());

        List<TelegramChatId> clientChatId = tryFindClientChatId();
        clientChatId.forEach(this::sendTelegramChatNewInformation);

//        emailService.sendSimpleMessage(order.getClient().getEmail(), "Order status changed",
//                createChangeOrderText());
    }

    private List<TelegramChatId> tryFindClientChatId() {
        return null;
////        FindTelegramChatIdRequest request = new FindTelegramChatIdRequest(order.getOrderNumber());
//        return telegramChatIdService.execute(request);
    }

    @SneakyThrows(TelegramApiException.class)
    private void sendTelegramChatNewInformation(TelegramChatId clientChatId) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(String.valueOf(clientChatId.getChatId()))
                .text(createChangeOrderText())
                .build();
        chatBot.execute(sendMessage);
    }

    private String createChangeOrderText() {
        return null;
//        return "New information about order number: " + order.getOrderNumber() + "\n"
//                + "Order date: " + order.getOrderDate() + "\n"
//                + "Total sum: " + order.getTotalSum() + "\n"
//                + "Order status: " + order.getOrderStatus().toString();
    }
}