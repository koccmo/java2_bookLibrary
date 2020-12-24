package team_VK.application.core.services.standart_validators;

import team_VK.application.core.responses.CoreError;
import team_VK.application.database.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
@DIComponent
public class ClientPersonalCodeFieldValidator {

    public List<CoreError> validate(String clientPersonalCode) {

        List<CoreError> errors = new ArrayList<>();
        if (clientPersonalCode.length() == 12) {

            IntStream clientPersonalCodeLeftSide = clientPersonalCode.substring(0, 5).chars();
            boolean clientPersonalCodeLeftSideIsNumeric = clientPersonalCodeLeftSide
                    .anyMatch(value -> (value >= 48) && (value <= 57));

            IntStream clientPersonalCodeRightSide = clientPersonalCode.substring(7, 11).chars();
            boolean clientPersonalCodeRightSideIsNumeric = clientPersonalCodeRightSide
                    .anyMatch(value -> (value >= 48) && (value <= 57));

            if (!(clientPersonalCodeLeftSideIsNumeric && clientPersonalCodeRightSideIsNumeric)) {
                CoreError error = new CoreError("clientPersonalCode", "Field clientPersonalCode haves illegal format. Should be 999999-99999");
                errors.add(error);
            }
        }
        if (clientPersonalCode.length() < 12) {
            CoreError error = new CoreError("clientPersonalCode", "Field clientPersonalCode is too short.");
            errors.add(error);
        }


        if (clientPersonalCode.length() > 12) {
            CoreError error = new CoreError("clientPersonalCode", "Field clientPersonalCode is too long.");
            errors.add(error);
        }
        return errors;
    }

}
