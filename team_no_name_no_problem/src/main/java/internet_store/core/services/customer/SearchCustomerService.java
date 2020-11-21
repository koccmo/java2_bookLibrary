package internet_store.core.services.customer;

import internet_store.core.requests.customer.SearchCustomerRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.SearchCustomerResponse;
import internet_store.database.customer.CustomerDatabase;

import java.util.ArrayList;
import java.util.List;

public class SearchCustomerService {

    private final CustomerDatabase customerDatabase;
    private final SearchCustomerRequestValidator searchCustomerRequestValidator;

    public SearchCustomerService(CustomerDatabase customerDatabase,
                                 SearchCustomerRequestValidator searchCustomerRequestValidator) {
        this.customerDatabase = customerDatabase;
        this.searchCustomerRequestValidator = searchCustomerRequestValidator;
    }

    public SearchCustomerResponse execute(SearchCustomerRequest searchCustomerRequest){

        List<CoreError> errors = searchCustomerRequestValidator.validate(searchCustomerRequest);

        if (!errors.isEmpty()){
            return new SearchCustomerResponse(errors, new ArrayList<>());
        }

        if (isNameAndSurnameNotEmpty(searchCustomerRequest.getName(), searchCustomerRequest.getSurname())){
            searchWhenNameAndSurnameAreProvided(searchCustomerRequest.getName(), searchCustomerRequest.getSurname());
        }

        if (isNameFilled(searchCustomerRequest.getName())){
            searchByNameIsProvided(searchCustomerRequest.getName());
        }

        return searchBySurnameIsProvided(searchCustomerRequest.getSurname());
    }

    private boolean isNameAndSurnameNotEmpty(String name, String surname){
        return !name.isEmpty() && !surname.isEmpty();
    }

    private SearchCustomerResponse searchWhenNameAndSurnameAreProvided(String name, String surname){
        List<CoreError> errors = new ArrayList<>();
        if (customerDatabase.findCustomersByNameAndSurname(name, surname).isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain customer with name: "
            + name + " and surname: " + surname));
        }
        if (!errors.isEmpty()){
            return new SearchCustomerResponse(errors, new ArrayList<>());
        }
        return new SearchCustomerResponse(customerDatabase.findCustomersByNameAndSurname(name, surname));
    }

    private boolean isNameFilled(String name){
        return !name.isEmpty();
    }

    private SearchCustomerResponse searchByNameIsProvided(String name){
        List<CoreError> errors = new ArrayList<>();
        if (customerDatabase.findAllCustomersByName(name).isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain customers with name: "
                    + name));
        }
        if (!errors.isEmpty()){
            return new SearchCustomerResponse(errors, new ArrayList<>());
        }
        return new SearchCustomerResponse(customerDatabase.findAllCustomersByName(name));
    }

    private SearchCustomerResponse searchBySurnameIsProvided(String surname){
        List<CoreError> errors = new ArrayList<>();
        if (customerDatabase.findAllCustomersBySurname(surname).isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain customers with surname: "
            + surname));
        }
        if (!errors.isEmpty()){
            return new SearchCustomerResponse(errors, new ArrayList<>());
        }
        return new SearchCustomerResponse(customerDatabase.findAllCustomersBySurname(surname));
    }
}
