package internet_store.core.service.ordering;

import internet_store.core.domain.Order;
import internet_store.core.request.ordering.FindByOrderNumberRequest;
import internet_store.database.order_database.InnerOrderDatabase;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class FindByOrderNumberService {
    @Autowired
    InnerOrderDatabase orderDatabase;

    public FindByOrderNumberService(InnerOrderDatabase orderDatabase) {
        this.orderDatabase = orderDatabase;
    }

    public Order execute(FindByOrderNumberRequest findByOrderNumberRequest) {
        return orderDatabase.findByOrderNumber(findByOrderNumberRequest.getOrderNumber());
    }
}