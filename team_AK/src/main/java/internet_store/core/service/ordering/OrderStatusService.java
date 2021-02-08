package internet_store.core.service.ordering;

import internet_store.core.domain.Order;
import internet_store.core.domain.TelegramChatId;
import internet_store.core.persistence.OrderRepository;
import internet_store.core.request.ordering.OrderStatusRequest;
import internet_store.core.service.telegram.FindTelegramChatIdService;
import internet_store.integration.mail.EmailServiceImpl;
import internet_store.integration.telegram.ChatBot;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Service
@Transactional
public class OrderStatusService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private FindTelegramChatIdService telegramChatIdService;
    @Autowired
    private ChatBot chatBot;
    @Autowired
    private EmailServiceImpl emailService;
    private OrderStatus orderStatus;
    private Order order;

    public void changeOrderStatus(String orderNumber, String orderStatus) {
        List<Order> orders = orderRepository.findAllByNumber(orderNumber);
        if (orders != null) {
            for (Order order : orders) {
                order.setStatus(orderStatus);
                orderRepository.save(order);
            }
            this.order = orders.get(0);
            if (orderStatus.equals("ORDER RECEIVED")) {
                sendEmailAboutConfirmationOrder();
            } else {
                sendEmailAboutChangedStatus();
            }
        }
    }

    public void sendEmailAboutConfirmationOrder() {
        emailService.sendSimpleMessage(order.getClient().getEmail(), "Order confirmed",
                createChangeOrderText());
    }

    private void sendEmailAboutChangedStatus() {
        emailService.sendSimpleMessage(order.getClient().getEmail(), "Order status changed",
                createChangeOrderText());
    }

    public void execute(OrderStatusRequest orderStatusRequest) {
        long orderId = orderStatusRequest.getOrderId();


//        order.setOrderStatus(orderStatusRequest.getOrderStatus());

        List<TelegramChatId> clientChatId = tryFindClientChatId();
        clientChatId.forEach(this::sendTelegramChatNewInformation);

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
        return "New information about order number: " + order.getNumber() + "\n"
                + "Order date: " + order.getDate() + "\n"
                + "Total sum: " + order.getTotal() + "\n"
                + "Order status: " + order.getStatus();
    }
}