package team_VK.application.core.services.standart_validators;

import org.springframework.stereotype.Component;
import team_VK.application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
@Component
public class ClientFirstNameFieldValidator {


    public List<CoreError> validate(String clientFirstName) {
        List<CoreError> errors = new ArrayList<>();
        if (clientFirstName == null || clientFirstName.equals("")) {
            CoreError error = new CoreError("clientFirstName", "Field clientFirstName must be not empty");
             errors.add(error);
            return errors;
        }

        if (clientFirstName.trim().equals("") && !clientFirstName.equals("")) {
            CoreError error = new CoreError("clientFirstName", "Field clientFirstName can't Space");
            errors.add(error);
        }

        if (clientFirstName.trim().length() <= 3 && !clientFirstName.trim().equals("")) {
            CoreError error = new CoreError("clientFirstName", "Field clientFirstName is too short");
            errors.add(error);
        }

        IntStream streamClientFirstName = clientFirstName.chars();
        boolean clientFirstNameContainsIllegalCharts = streamClientFirstName
                .anyMatch(value -> (value <= 31) ||
                        ((value >= 33) && (value <= 44)) ||
                        ((value >= 46) && (value <= 64)) ||
                        ((value >= 91) && (value <= 96)) ||

                        (value >= 123));
        if (clientFirstNameContainsIllegalCharts) {
            CoreError error = new CoreError("clientFirstName", "Field clientFirstName contains illegal characters");
            errors.add(error);
        }
        return errors;
    }
}
