package internet_store.core.services.customer.validators;

import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component public class AddCustomerRequestValidator {

    public List<CoreError> validate (AddCustomerRequest addCustomerRequest) {
        List<CoreError> errors = new ArrayList<>();

        errors.addAll(nameValidationErrors(addCustomerRequest.getCustomer().getName()));
        errors.addAll(surnameValidationErrors(addCustomerRequest.getCustomer().getSurname()));
        errors.addAll(phoneValidationErrors(addCustomerRequest.getCustomer().getPhoneNumber()));
        errors.addAll(addressValidationErrors(addCustomerRequest.getCustomer().getAddress()));
        errors.addAll(emailValidationErrors(addCustomerRequest.getCustomer().getEmail()));

        return errors;
    }

    private List<CoreError> nameValidationErrors(String name) {
        List <CoreError> errors = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            errors.add(new CoreError("name", "Name can't be empty"));
        }
        if (!containsOnlyLetters(name)){
            errors.add(new CoreError("name","Not valid input for name, should contain only letters"));
        }
        return errors;
    }

    private List<CoreError> surnameValidationErrors(String surname) {
        List<CoreError> errors = new ArrayList<>();
        if (surname == null || surname.isEmpty()) {
            errors.add(new CoreError("surname", "Surname can't be empty"));
        }
        if (!containsOnlyLetters(surname)){
            errors.add(new CoreError("surname", "Not valid input for surname, should contain only letters"));
        }
        return errors;
    }

    private List<CoreError> phoneValidationErrors(String phone) {
        List<CoreError> errors = new ArrayList<>();
        if (phone == null || phone.isEmpty()) {
            errors.add(new CoreError("phone number", "Phone can't be empty"));
        }
        if (!numberContainsOnlyDigits(phone) || ((phone.length() != 8) && (phone.length() != 11))){
            errors.add(new CoreError("phone number", "Not valid input for phone number, should contain only 8 or 11 digits"));
        }
        return errors;
    }

    private List<CoreError> addressValidationErrors (String address) {
        List<CoreError> errors = new ArrayList<>();
        if (address == null || address.isEmpty()) {
            errors.add(new CoreError("address", "Not valid input for address"));
        }
        return errors;
    }

    private List<CoreError> emailValidationErrors (String email) {
        List <CoreError> errors = new ArrayList<>();
        if (email == null || email.isEmpty()) {
            errors.add(new CoreError("e-mail", "E-mail can't be empty"));
        } else
        if (notValidEmailFormat(email)) {
            errors.add(new CoreError("e-mail", "Not valid e-mail format"));
        }
        return errors;
    }

    private boolean notValidEmailFormat(String email) {
        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);
        if (email == null){
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return !matcher.matches();
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
