package internet_store.integration;

import internet_store.core.domain.Order;
import internet_store.core.operation.Tax;
import internet_store.integration.telegram.ChatBot;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class PrintService {
    @Autowired
    private ChatBot chatBot;
    @Autowired
    private Tax tax;

    public String createPrintReport(Order order) {
        return "New information about order number: " + order.getNumber() + "\n"
                + "Order date: " + order.getDate() + "\n"
                + "Total sum: " + order.getTotal() + " " + tax.getCurrencySymbol() + "\n"
                + "Order status: " + order.getStatus();
    }

    @SneakyThrows(TelegramApiException.class)
    public void printStartMessage(long chatId) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text("Enter order number for information")
                .build();
        chatBot.execute(sendMessage);
    }

    @SneakyThrows(TelegramApiException.class)
    public void printNoFoundOrder(long chatId) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text("Order not found")
                .build();
        chatBot.execute(sendMessage);
    }

    @SneakyThrows(TelegramApiException.class)
    public void printOrder(Order order, long chatId) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(createPrintReport(order))
                .build();
        chatBot.execute(sendMessage);
    }
}