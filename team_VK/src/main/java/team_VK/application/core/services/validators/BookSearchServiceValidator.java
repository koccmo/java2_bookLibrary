package team_VK.application.core.services.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team_VK.application.core.requests.BookSearchRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.standart_validators.AuthorFieldValidator;
import team_VK.application.core.services.standart_validators.BookIDFieldValidator;
import team_VK.application.core.services.standart_validators.TitleFieldValidator;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookSearchServiceValidator {
    @Autowired
    public TitleFieldValidator titleFieldValidator;
    @Autowired
    public AuthorFieldValidator authorFieldValidator;
    @Autowired
    public BookIDFieldValidator bookIDFieldValidator;

    public List<CoreError> validate(BookSearchRequest request) {

        List<CoreError> errors = new ArrayList<>();

        switch (request.getSearchCriteria()) {
            case 1:
                return titleFieldValidator.validate(request.getCriteriaValue());
            case 2:
                return authorFieldValidator.validate(request.getCriteriaValue());
            case 3:
                return bookIDFieldValidator.validate(request.getCriteriaValue());
            default:
                return errors;
        }
    }
}
