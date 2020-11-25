package internet_store_1.core.services.customer;

import internet_store_1.core.domain.Customer;
import internet_store_1.core.requests.customer.FindAllCustomersByNameAndSurnameRequest;
import internet_store_1.core.response.CoreError;
import internet_store_1.core.response.customer.FindAllCustomersByNameAndSurnameResponse;
import internet_store_1.database.customer.CustomerDatabase;

import java.util.ArrayList;
import java.util.List;


public class FindAllCustomersByNameAndSurnameService {

    private final CustomerDatabase customerDatabase;
    private final FindAllCustomersByNameAndSurnameValidator findAllCustomersByNameAndSurnameValidator;


    public FindAllCustomersByNameAndSurnameService(CustomerDatabase customerDatabase,
                                                   FindAllCustomersByNameAndSurnameValidator
                                                           findAllCustomersByNameAndSurnameValidator) {
        this.customerDatabase = customerDatabase;
        this.findAllCustomersByNameAndSurnameValidator = findAllCustomersByNameAndSurnameValidator;
    }

    public FindAllCustomersByNameAndSurnameResponse execute(FindAllCustomersByNameAndSurnameRequest
                                                                    findAllCustomersByNameAndSurnameRequest){

        List<CoreError> errors = findAllCustomersByNameAndSurnameValidator
                .validate(findAllCustomersByNameAndSurnameRequest);

        if (!errors.isEmpty()){
            return new FindAllCustomersByNameAndSurnameResponse(errors, new ArrayList<>());
        }

        List <Customer> customers = customerDatabase.findCustomersByNameAndSurname(findAllCustomersByNameAndSurnameRequest.getName(),
                findAllCustomersByNameAndSurnameRequest.getSurname());

        if (customers.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain customer with name " +
                    findAllCustomersByNameAndSurnameRequest.getName() + " and surname " +
                    findAllCustomersByNameAndSurnameRequest.getSurname()));
            return new FindAllCustomersByNameAndSurnameResponse(errors, new ArrayList<>());
        }
            return new FindAllCustomersByNameAndSurnameResponse(customers);


    }
}
