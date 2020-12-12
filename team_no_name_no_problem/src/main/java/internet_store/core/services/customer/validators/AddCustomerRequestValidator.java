package internet_store.core.services.customer.validators;

import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AddCustomerRequestValidator {

    public List<CoreError> validate (AddCustomerRequest addCustomerRequest) {
        List<CoreError> errors = new ArrayList<>();

        if (addCustomerRequest.getCustomer().getName() == null || addCustomerRequest.getCustomer().getName()
                .isEmpty()) {
            errors.add(new CoreError("name", "Not valid input for name"));
        }
        if (!containsOnlyLetters(addCustomerRequest.getCustomer().getName())){
            errors.add(new CoreError("name","Not valid input for name, should contain only letters"));
        }
        if (addCustomerRequest.getCustomer().getSurname() == null || addCustomerRequest.getCustomer().getSurname()
                .isEmpty()) {
            errors.add(new CoreError("surname", "Not valid input for surname"));
        }
        if (!containsOnlyLetters(addCustomerRequest.getCustomer().getSurname())){
            errors.add(new CoreError("surname", "Not valid input for surname, should contain only letters"));
        }
        if (addCustomerRequest.getCustomer().getPhoneNumber() == null || addCustomerRequest.getCustomer().getPhoneNumber()
                .isEmpty()) {
            errors.add(new CoreError("phone number", "Not valid input for phone number"));
        }
        if (!numberContainsOnlyDigits(addCustomerRequest.getCustomer().getPhoneNumber())){
            errors.add(new CoreError("phone number", "Not valid input for phone number, should contain only digits"));
        }
        if (addCustomerRequest.getCustomer().getAddress() == null || addCustomerRequest.getCustomer().getAddress()
                .isEmpty()) {
            errors.add(new CoreError("address", "Not valid input for address"));
        }
        if (addCustomerRequest.getCustomer().getEmail() == null || addCustomerRequest.getCustomer().getEmail()
                .isEmpty()) {
            errors.add(new CoreError("e-mail", "Not valid input for e-mail"));
        }
        return errors;
    }

    private boolean numberContainsOnlyDigits(String input){
        String regex = "[0-9]+";

        Pattern pattern = Pattern.compile(regex);
        if (input == null){
            return false;
        }
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private boolean containsOnlyLetters(String input){
        String regex = "[a-zA-Z]+";

        Pattern pattern = Pattern.compile(regex);
        if (input == null){
            return false;
        }
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

}
