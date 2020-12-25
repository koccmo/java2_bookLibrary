package team_VK.application.core.services.validators;

import team_VK.application.core.requests.BookSearchRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.DIDependency;
import team_VK.application.core.services.standart_validators.AuthorFieldValidator;
import team_VK.application.core.services.standart_validators.BookIDFieldValidator;
import team_VK.application.core.services.standart_validators.TitleFieldValidator;
import team_VK.application.database.DIComponent;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class BookSearchServiceValidator {
    @DIDependency
    public TitleFieldValidator titleFieldValidator;
    @DIDependency
    public AuthorFieldValidator authorFieldValidator;
    @DIDependency
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
