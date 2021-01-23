package lesson_10.core.services;

import lesson_10.core.database.ElectronicLibrary;
import lesson_10.core.requests.DeleteBookByAuthorRequest;
import lesson_10.core.responses.CoreError;
import lesson_10.core.responses.DeleteBookByAuthorResponse;
import lesson_10.core.services.validators.DeleteBookByAuthorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteBookByAuthorService {

    @Autowired
    private ElectronicLibrary electronicLibrary;

    @Autowired
    private DeleteBookByAuthorValidator validator;

    public DeleteBookByAuthorResponse deleteBookByAuthorResponse(DeleteBookByAuthorRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteBookByAuthorResponse(errors);
        }
        boolean isRemoved = electronicLibrary.deleteBookByAuthor(request.getBookAuthor());
        return new DeleteBookByAuthorResponse(isRemoved);
    }
}
