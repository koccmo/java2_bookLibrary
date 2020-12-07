package internet_store.core.service.ordering;

import internet_store.core.domain.Order;
import internet_store.core.request.ordering.FindByOrderNumberRequest;
import internet_store.database.order_database.InnerOrderDatabase;
import dependency.annotation.DIComponent;
import dependency.annotation.DIDependency;

@DIComponent
public class FindByOrderNumberService {
    @DIDependency
    InnerOrderDatabase orderDatabase;

    public FindByOrderNumberService() {
    }

    public FindByOrderNumberService(InnerOrderDatabase orderDatabase) {
        this.orderDatabase = orderDatabase;
    }

    public Order execute(FindByOrderNumberRequest findByOrderNumberRequest) {
        return orderDatabase.findByOrderNumber(findByOrderNumberRequest.getOrderNumber());
    }
}