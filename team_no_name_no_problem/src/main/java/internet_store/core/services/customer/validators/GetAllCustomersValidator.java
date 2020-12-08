package internet_store.core.services.customer.validators;

import internet_store.core.requests.customer.GetAllCustomersRequest;
import internet_store.core.response.CoreError;
import internet_store.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;


public class GetAllCustomersValidator {

    public List<CoreError> validate (GetAllCustomersRequest getAllCustomersRequest){

        return new ArrayList<>();

    }
}
