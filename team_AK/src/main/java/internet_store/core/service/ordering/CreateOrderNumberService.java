package internet_store.core.service.ordering;

import internet_store.date_formats.DateCreator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderNumberService {
    private final DateCreator dateCreator = new DateCreator();
    @Value("${order-number}")
    @Getter
    @Setter
    private Integer orderNumber;
    @Getter
    private String fullOrderNumber;
    @Setter
    @Getter
    private Boolean orderHaveNumber = false;

    public String createOrderNumber() {
        fullOrderNumber = dateCreator.createShortDateFormat() + "/" + orderNumber.toString();
        orderNumber++;
        orderHaveNumber = true;
        return fullOrderNumber;
    }
}