package internet_store.core.services.customer;

import internet_store.core.requests.customer.FindCustomerBySurnameRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.FindCustomerBySurnameResponse;
import internet_store.database.customer.CustomerDatabase;
import internet_store.core.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class FindAllCustomersBySurnameService {

    private final CustomerDatabase customerDatabase;
    private final FindCustomerBySurnameValidator findCustomerBySurnameValidator;

    public FindAllCustomersBySurnameService(CustomerDatabase customerDatabase, FindCustomerBySurnameValidator findCustomerBySurnameValidator) {
        this.customerDatabase = customerDatabase;
        this.findCustomerBySurnameValidator = findCustomerBySurnameValidator;
    }

    public FindCustomerBySurnameResponse execute(FindCustomerBySurnameRequest findCustomerBySurnameRequest){
        List<CoreError> errors = findCustomerBySurnameValidator.validate(findCustomerBySurnameRequest);
        if (!errors.isEmpty()){
            return new FindCustomerBySurnameResponse(errors, new ArrayList<>());
        }

        if (customerDatabase.findAllCustomersBySurname(findCustomerBySurnameRequest.getSurname()).isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain such customer surname"
            + findCustomerBySurnameRequest.getSurname()));
        return new FindCustomerBySurnameResponse(errors, new ArrayList<>());
    }
        return new FindCustomerBySurnameResponse(customerDatabase.findAllCustomersBySurname
                (findCustomerBySurnameRequest.getSurname()));
    }
}
