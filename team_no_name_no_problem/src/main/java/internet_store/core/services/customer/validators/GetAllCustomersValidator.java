package internet_store.core.services.customer.validators;

import internet_store.core.requests.customer.GetAllCustomersRequest;
import internet_store.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component public class GetAllCustomersValidator {

    public List<CoreError> validate (GetAllCustomersRequest getAllCustomersRequest){

        return new ArrayList<>();

    }
}
