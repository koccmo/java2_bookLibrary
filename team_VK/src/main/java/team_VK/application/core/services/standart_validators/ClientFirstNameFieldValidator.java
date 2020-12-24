package team_VK.application.core.services.standart_validators;

import team_VK.application.core.responses.CoreError;
import team_VK.application.database.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
@DIComponent
public class ClientFirstNameFieldValidator {


    public List<CoreError> validate(String clientFirstName) {
        List<CoreError> errors = new ArrayList<>();
        if (clientFirstName == null || clientFirstName.equals("")) {
            CoreError error = new CoreError("clientFirstName", "Field clientFirstName must be not empty");
            errors.add(error);
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
                        ((value >= 123) && (value <= 157)) ||
                        ((value >= 174) && (value <= 180)) ||
                        ((value >= 185) && (value <= 207)) ||
                        ((value >= 217) && (value <= 220)) ||
                        (value == 223) ||
                        (value >= 253));
        if (clientFirstNameContainsIllegalCharts) {
            CoreError error = new CoreError("clientFirstName", "Field clientFirstName contains illegal characters");
            errors.add(error);
        }
        return errors;
    }
}
