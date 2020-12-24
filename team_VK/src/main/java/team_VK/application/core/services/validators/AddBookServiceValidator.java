package team_VK.application.core.services.validators;

import team_VK.application.core.requests.AddBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.DIDependency;
import team_VK.application.core.services.standart_validators.AuthorFieldValidator;
import team_VK.application.core.services.standart_validators.TitleFieldValidator;
import team_VK.application.database.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DIComponent
public class AddBookServiceValidator {

    @DIDependency
    public AuthorFieldValidator authorFieldValidator;
    @DIDependency public TitleFieldValidator titleFieldValidator;

    public List<CoreError> validate(AddBookRequest request) {

        List<CoreError>  errors1 = titleFieldValidator.validate(request.getBookTitle());
        List<CoreError>  errors2 = authorFieldValidator.validate(request.getBookAuthor());
        List<CoreError>  errors = Stream.concat(
                errors1.stream(), errors2.stream())
                .collect(Collectors.toList());
        return errors;
    }



}
