package lesson_4.core.services;

import lesson_4.core.database.ElectronicLibrary;
import lesson_4.core.requests.DeleteBookByIdRequest;
import lesson_4.core.responses.CoreError;
import lesson_4.core.responses.DeleteBookByIdResponse;
import lesson_4.core.services.validators.DeleteBookByIdValidator;

import java.util.List;

public class DeleteBookByIdService {

    private final ElectronicLibrary electronicLibrary;
    private final DeleteBookByIdValidator validator;

    public DeleteBookByIdService(ElectronicLibrary electronicLibrary, DeleteBookByIdValidator validator) {

        this.electronicLibrary = electronicLibrary;
        this.validator = validator;
    }

    public DeleteBookByIdResponse deleteBookByIdResponse(DeleteBookByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        Long id = request.getBookIdToDelete();
        if (!errors.isEmpty()) {
            return new DeleteBookByIdResponse(errors);
        } else return new DeleteBookByIdResponse(electronicLibrary.deleteBookById(id));
    }
}

