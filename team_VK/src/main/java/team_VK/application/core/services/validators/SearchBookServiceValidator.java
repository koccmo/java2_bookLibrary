package team_VK.application.core.services.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team_VK.application.core.requests.SearchBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.standart_validators.AuthorFieldValidator;
import team_VK.application.core.services.standart_validators.BookIDFieldValidator;
import team_VK.application.core.services.standart_validators.TitleFieldValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SearchBookServiceValidator {

    @Autowired
    public AuthorFieldValidator authorFieldValidator;
    @Autowired
    public TitleFieldValidator titleFieldValidator;
    @Autowired
    public BookIDFieldValidator bookIDFieldValidator;


    public List<CoreError> validate(SearchBookRequest request) {

        List<CoreError> errors;
        List<CoreError> errors1 = new ArrayList<>();
        List<CoreError> errors2 = new ArrayList<>();
        List<CoreError> errors3 = new ArrayList<>();


        if (!request.getBookTitle().equals(""))
            errors1 = titleFieldValidator.validate(request.getBookTitle());

        if (!request.getBookAuthor().equals("")) {
            errors2 = authorFieldValidator.validate(request.getBookAuthor());
        }
        if (!request.getBookIdString().equals(""))
            errors3 = bookIDFieldValidator.validate(request.bookIdString);

        errors = Stream.concat(
                errors1.stream(), errors2.stream())
                .collect(Collectors.toList());

        errors = Stream.concat(
                errors.stream(), errors3.stream())
                .collect(Collectors.toList());

        return errors;
    }


}
