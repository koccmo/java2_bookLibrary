package adventure_time.core.services.customers;

import adventure_time.core.requests.customers.LoginCustomerRequest;
import adventure_time.core.requests.customers.UpdateCustomerRequest;
import adventure_time.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class LoginCustomerRequestValidator {

    private static final Pattern PATTERN_EMAIL = Pattern.compile("^([a-z0-9_\\.-]+)@([a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_PASSWORD = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");

    public List<CoreError> validate (LoginCustomerRequest request) {

        List<CoreError> errors = new ArrayList<>();

        boolean nullEmail = request.getEmail() == null || request.getEmail().isBlank();
        if (nullEmail) {
            CoreError error = new CoreError("customerEmail", "Must be not null/empty");
            errors.add(error);
        }

        if (!nullEmail && !PATTERN_EMAIL.matcher(request.getEmail()).matches()) {
            CoreError error = new CoreError("customerEmail", "Email is incorrect");
            errors.add(error);
        }

        boolean nullPassword = request.getPassword() == null || request.getPassword().isBlank();
        if (nullPassword) {
            CoreError error = new CoreError("customerPassword", "Must be not null/empty");
            errors.add(error);
        }
        if (!nullPassword && !PATTERN_PASSWORD.matcher(request.getPassword().toString()).matches()) {
            CoreError error = new CoreError("customerPassword", "Password is incorrect");
            errors.add(error);
        }

        return errors;
    }
}
