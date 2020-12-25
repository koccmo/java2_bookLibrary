package internet_store.core.service.ordering;

import internet_store.database.order_database.InnerOrderDatabase;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class PrintOrderService {
    @Autowired
    InnerOrderDatabase orderDatabase;

    public void print() {
        if (orderDatabase.isEmpty()) {
            System.out.println("No records");
            return;
        }
        System.out.println("Orders list:");
        orderDatabase.showReport();
    }
}