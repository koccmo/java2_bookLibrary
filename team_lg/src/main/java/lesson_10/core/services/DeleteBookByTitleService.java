package lesson_10.core.services;

import lesson_10.core.database.ElectronicLibrary;
import lesson_10.core.requests.DeleteBookByTitleRequest;
import lesson_10.core.responses.CoreError;
import lesson_10.core.responses.DeleteBookByTitleResponse;
import lesson_10.core.services.validators.DeleteBookByTitleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteBookByTitleService {

    @Autowired
    private ElectronicLibrary electronicLibrary;

    @Autowired
    private DeleteBookByTitleValidator validator;

    public DeleteBookByTitleResponse deleteBookByTitleResponse(DeleteBookByTitleRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteBookByTitleResponse(errors);
        }
        boolean isRemoved = electronicLibrary.deleteBookByTitle(request.getBookTitle());
        return new DeleteBookByTitleResponse(isRemoved);
    }
}
