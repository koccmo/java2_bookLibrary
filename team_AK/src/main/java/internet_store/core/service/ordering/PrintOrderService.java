package internet_store.core.service.ordering;

import internet_store.database.order_database.InnerOrderDatabase;

public class PrintOrderService {
    private final InnerOrderDatabase orderDatabase;

    public PrintOrderService(InnerOrderDatabase orderDatabase) {
        this.orderDatabase = orderDatabase;
    }

    public void print() {
        if (orderDatabase.isEmpty()) {
            System.out.println("No records");
            return;
        }
        System.out.println("Orders list:");
        orderDatabase.showReport();
    }
}