package lesson_5.core.services;

import lesson_5.core.database.ElectronicLibrary;
import lesson_5.core.requests.DeleteBookByAuthorRequest;
import lesson_5.core.responses.CoreError;
import lesson_5.core.responses.DeleteBookByAuthorResponse;
import lesson_5.core.services.validators.DeleteBookByAuthorValidator;

import java.util.List;

public class DeleteBookByAuthorService {
    private final ElectronicLibrary electronicLibrary;
    private final DeleteBookByAuthorValidator validator;

    public DeleteBookByAuthorService(ElectronicLibrary electronicLibrary, DeleteBookByAuthorValidator validator) {
        this.electronicLibrary = electronicLibrary;
        this.validator = validator;
    }

    public DeleteBookByAuthorResponse deleteBookByAuthorResponse(DeleteBookByAuthorRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteBookByAuthorResponse(errors);
        }
        boolean isRemoved = electronicLibrary.deleteBookByAuthor(request.getBookAuthor());
        return new DeleteBookByAuthorResponse(isRemoved);
    }
}
