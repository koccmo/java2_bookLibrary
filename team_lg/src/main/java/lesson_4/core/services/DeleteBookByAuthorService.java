package lesson_4.core.services;

import lesson_4.core.database.ElectronicLibrary;
import lesson_4.core.requests.DeleteBookByAuthorRequest;
import lesson_4.core.responses.CoreError;
import lesson_4.core.responses.DeleteBookByAuthorResponse;
import lesson_4.core.services.validators.DeleteBookByAuthorValidator;

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
