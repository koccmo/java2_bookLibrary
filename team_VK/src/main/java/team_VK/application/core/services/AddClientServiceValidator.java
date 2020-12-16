package team_VK.application.core.services;

import team_VK.application.core.requests.AddClientRequest;
import team_VK.application.core.responses.AddClientResponse;
import team_VK.application.core.responses.CoreError;
import team_VK.application.database.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
@DIComponent
public class AddClientServiceValidator {

    List<CoreError> errors = new ArrayList<>();

    public List<CoreError> validate(AddClientRequest request) {

        if (request.getClientFirstName() == null || request.getClientFirstName().equals("")) {
            CoreError error = new CoreError("clientFirstName", "Field clientFirstName must be not empty");
            errors.add(error);
        }

        if (request.getClientFirstName().trim().equals("") && !request.getClientFirstName().equals("")) {
            CoreError error = new CoreError("clientFirstName", "Field clientFirstName can't Space");
            errors.add(error);
        }

        if (request.getClientFirstName().trim().length() <= 3 && !request.getClientFirstName().trim().equals("")) {
            CoreError error = new CoreError("clientFirstName", "Field clientFirstName is too short");
            errors.add(error);
        }

        IntStream streamClientFirstName = request.getClientFirstName().chars();
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


        if (request.getClientLastName() == null || request.getClientLastName().equals("")) {
            CoreError error = new CoreError("clientLastName", "Field clientLastName must be not empty");
            errors.add(error);
        }

        if (request.getClientLastName().trim().equals("") && !request.getClientLastName().equals("")) {
            CoreError error = new CoreError("clientLastName", "Field clientLastName can't be Space");
            errors.add(error);
        }

        if (request.getClientLastName().trim().length() <= 3 && !request.getClientLastName().trim().equals("")) {
            CoreError error = new CoreError("clientLastName", "Field clientLastName is too short");
            errors.add(error);
        }

        IntStream streamClientLastName = request.getClientLastName().chars();
        boolean clientLastNameContainsIllegalCharts = streamClientLastName
                .anyMatch(value -> (value <= 31) ||
                        ((value >= 33) && (value <= 44)) ||
                        ((value >= 46) && (value <= 64)) ||
                        ((value >= 91) && (value <= 96)) ||
                        ((value >= 123) && (value <= 159)) ||
                        ((value >= 174) && (value <= 180)) ||
                        ((value >= 185) && (value <= 207)) ||
                        ((value >= 217) && (value <= 220)) ||
                        (value == 223) ||
                        (value >= 253));
        if (clientLastNameContainsIllegalCharts) {
            CoreError error = new CoreError("clientLastName", "Field clientLastName contains illegal characters");
            errors.add(error);
        }

        if (request.getClientPersonalCode().length() == 12) {

            IntStream clientPersonalCodeLeftSide = request.getClientPersonalCode().substring(0, 5).chars();
            boolean clientPersonalCodeLeftSideIsNumeric = clientPersonalCodeLeftSide
                    .anyMatch(value -> (value >= 48) && (value <= 57));

            IntStream clientPersonalCodeRightSide = request.getClientPersonalCode().substring(7, 11).chars();
            boolean clientPersonalCodeRightSideIsNumeric = clientPersonalCodeRightSide
                    .anyMatch(value -> (value >= 48) && (value <= 57));

            if (!(clientPersonalCodeLeftSideIsNumeric && clientPersonalCodeRightSideIsNumeric)){
                CoreError error = new CoreError("clientPersonalCode", "Field clientPersonalCode haves illegal format. Should be 999999-99999");
                errors.add(error);
            }
        }
        if (request.getClientPersonalCode().length() < 12) {
            CoreError error = new CoreError("clientPersonalCode", "Field clientPersonalCode is too short.");
            errors.add(error);
        }


        if (request.getClientPersonalCode().length() > 12) {
            CoreError error = new CoreError("clientPersonalCode", "Field clientPersonalCode is too long.");
            errors.add(error);
        }


        return errors;
    }

}
