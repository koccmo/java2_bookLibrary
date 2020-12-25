package team_VK.application.core.services.validators;

import team_VK.application.core.requests.AddClientRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.DIDependency;
import team_VK.application.core.services.standart_validators.ClientFirstNameFieldValidator;
import team_VK.application.core.services.standart_validators.ClientLastNameFieldValidator;
import team_VK.application.core.services.standart_validators.ClientPersonalCodeFieldValidator;
import team_VK.application.database.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DIComponent
public class AddClientServiceValidator {

    @DIDependency
    ClientFirstNameFieldValidator clientFirstNameFieldValidator;
    @DIDependency
    ClientLastNameFieldValidator clientLastNameFieldValidator;
    @DIDependency
    ClientPersonalCodeFieldValidator clientPersonalCodeFieldValidator;

    List<CoreError> errors1 = new ArrayList<>();
    List<CoreError> errors2 = new ArrayList<>();
    List<CoreError> errors3 = new ArrayList<>();
    List<CoreError> errors = new ArrayList<>();

    public List<CoreError> validate(AddClientRequest request) {

        errors1 = clientFirstNameFieldValidator.validate(request.getClientFirstName());
        errors2 = clientLastNameFieldValidator.validate(request.getClientLastName());
        errors3 = clientPersonalCodeFieldValidator.validate(request.getClientPersonalCode());

        errors = Stream.concat(errors1.stream(), errors2.stream())
                .collect(Collectors.toList());
        errors = Stream.concat(errors.stream(), errors3.stream())
                .collect(Collectors.toList());

        return errors;
    }

}
