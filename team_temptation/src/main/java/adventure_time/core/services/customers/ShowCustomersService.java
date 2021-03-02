package adventure_time.core.services.customers;

import adventure_time.core.database.customers.CustomerRepository;
import adventure_time.core.domain.Customers;
import adventure_time.core.requests.customers.ShowCustomersRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.customer.ShowCustomersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowCustomersService {

    @Autowired
    private CustomerRepository database;
    @Autowired
    ShowCustomersRequestValidator validator;

    public ShowCustomersResponse getCustomersList(ShowCustomersRequest request) {

        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ShowCustomersResponse(null, errors);
        }
        //TODO валидация не проходит из-за ""

        String query = "FROM Customers AS c";
        if (!request.getNameStartsWith().equals("")) {
            if (!request.getPhoneStartsWith().equals("")) {
                query = query + " WHERE c.customerName LIKE '" +
                        request.getNameStartsWith() +
                        "%' AND c.customerPhone LIKE '+" +
                        request.getPhoneStartsWith() + "%'";
            } else {
                query = query + " WHERE c.customerName LIKE '" +
                        request.getNameStartsWith() + "%'";
            }

        } else {
            query = query + " WHERE c.customerPhone LIKE '+" +
            request.getPhoneStartsWith() + "%'";
        }

        if (!request.getOrdering().getOrderBy().equals("")) {
            query = query + request.getOrdering().getOrderBy();
        }

        if (!request.getOrdering().getOrderDirection().equals("")) {
            query = query + request.getOrdering().getOrderDirection();
        }

        query = query + " LIMIT " + request.getPaging().getPageSize() + "," + request.getPaging().getPageNumber();

        List<Customers> result = database.findCustomers(query);

        return new ShowCustomersResponse(result,null);
    }

}
