package internet_store.core.service.ordering;

import internet_store.database.order_database.InnerOrderDatabase;
import dependency.annotation.DIComponent;
import dependency.annotation.DIDependency;

@DIComponent
public class PrintOrderService {
    @DIDependency
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