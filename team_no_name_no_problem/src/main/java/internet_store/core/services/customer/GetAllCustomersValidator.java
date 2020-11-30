package internet_store.core.services.customer;

import internet_store.core.requests.customer.GetAllCustomersRequest;
import internet_store.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;

public class GetAllCustomersValidator {

    public List<CoreError> validate (GetAllCustomersRequest getAllCustomersRequest){

        return new ArrayList<>();

    }
}
