package lesson_4.core.services;

import lesson_4.core.database.ElectronicLibrary;
import lesson_4.core.requests.DeleteBookByTitleRequest;
import lesson_4.core.responses.CoreError;
import lesson_4.core.responses.DeleteBookByTitleResponse;
import lesson_4.core.services.validators.DeleteBookByTitleValidator;

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
