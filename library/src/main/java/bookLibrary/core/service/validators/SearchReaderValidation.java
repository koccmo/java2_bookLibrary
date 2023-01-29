package bookLibrary.core.service.validators;

import bookLibrary.core.request.SearchReaderRequest;
import bookLibrary.core.response.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class SearchReaderValidation {
    @Autowired
    private ReaderSearchFieldValidator fieldValidator;
    @Autowired
    private OrderingValidator orderingValidator;
    @Autowired
    private PagingValidator pagingValidator;

    public List<CoreError> validate(SearchReaderRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(fieldValidator.validate(request));
        errors.addAll(orderingValidator.validate(request.getOrdering()));
        errors.addAll(pagingValidator.validate(request.getPaging()));
        return errors;
    }
}
