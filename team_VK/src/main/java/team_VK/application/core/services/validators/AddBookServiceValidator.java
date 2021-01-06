package team_VK.application.core.services.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team_VK.application.core.requests.AddBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.standart_validators.AuthorFieldValidator;
import team_VK.application.core.services.standart_validators.TitleFieldValidator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AddBookServiceValidator {

    @Autowired
    public AuthorFieldValidator authorFieldValidator;
    @Autowired public TitleFieldValidator titleFieldValidator;

    public List<CoreError> validate(AddBookRequest request) {

        List<CoreError>  errors1 = titleFieldValidator.validate(request.getBookTitle());
        List<CoreError>  errors2 = authorFieldValidator.validate(request.getBookAuthor());
        List<CoreError>  errors = Stream.concat(
                errors1.stream(), errors2.stream())
                .collect(Collectors.toList());
        return errors;
    }



}
