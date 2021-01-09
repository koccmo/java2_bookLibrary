package lesson_5.core.services;

import lesson_5.core.database.ElectronicLibrary;
import lesson_5.core.requests.DeleteBookByTitleRequest;
import lesson_5.core.responses.CoreError;
import lesson_5.core.responses.DeleteBookByTitleResponse;
import lesson_5.core.services.validators.DeleteBookByTitleValidator;

import java.util.List;

public class DeleteBookByTitleService {

    private ElectronicLibrary electronicLibrary;
    private DeleteBookByTitleValidator validator;

    public DeleteBookByTitleService(ElectronicLibrary electronicLibrary, DeleteBookByTitleValidator validator) {
        this.electronicLibrary = electronicLibrary;
        this.validator = validator;
    }

    public DeleteBookByTitleResponse deleteBookByTitleResponse(DeleteBookByTitleRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteBookByTitleResponse(errors);
        }
        boolean isRemoved = electronicLibrary.deleteBookByTitle(request.getBookTitle());
        return new DeleteBookByTitleResponse(isRemoved);
    }
}
