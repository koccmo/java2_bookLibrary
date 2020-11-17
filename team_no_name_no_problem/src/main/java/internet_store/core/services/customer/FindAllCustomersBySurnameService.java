package internet_store.core.services.customer;

import internet_store.core.requests.customer.FindAllCustomersBySurnameRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.FindAllCustomersBySurnameResponse;
import internet_store.database.customer.CustomerDatabase;

import java.util.ArrayList;
import java.util.List;

public class FindAllCustomersBySurnameService {

    private final CustomerDatabase customerDatabase;
    private final FindAllCustomersBySurnameValidator findAllCustomersBySurnameValidator;

    public FindAllCustomersBySurnameService(CustomerDatabase customerDatabase, FindAllCustomersBySurnameValidator findAllCustomersBySurnameValidator) {
        this.customerDatabase = customerDatabase;
        this.findAllCustomersBySurnameValidator = findAllCustomersBySurnameValidator;
    }

    public FindAllCustomersBySurnameResponse execute(FindAllCustomersBySurnameRequest findAllCustomersBySurnameRequest){
        List<CoreError> errors = findAllCustomersBySurnameValidator.validate(findAllCustomersBySurnameRequest);
        if (!errors.isEmpty()){
            return new FindAllCustomersBySurnameResponse(errors, new ArrayList<>());
        }

        if (customerDatabase.findAllCustomersBySurname(findAllCustomersBySurnameRequest.getSurname()).isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain such customer surname"
            + findAllCustomersBySurnameRequest.getSurname()));
        return new FindAllCustomersBySurnameResponse(errors, new ArrayList<>());
    }
        return new FindAllCustomersBySurnameResponse(customerDatabase.findAllCustomersBySurname
                (findAllCustomersBySurnameRequest.getSurname()));
    }
}
