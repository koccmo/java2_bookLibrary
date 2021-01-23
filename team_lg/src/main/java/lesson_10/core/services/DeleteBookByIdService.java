package lesson_10.core.services;

import lesson_10.core.database.ElectronicLibrary;
import lesson_10.core.requests.DeleteBookByIdRequest;
import lesson_10.core.responses.CoreError;
import lesson_10.core.responses.DeleteBookByIdResponse;
import lesson_10.core.services.validators.DeleteBookByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteBookByIdService {

    @Autowired
    private ElectronicLibrary electronicLibrary;

    @Autowired
    private DeleteBookByIdValidator validator;

    public DeleteBookByIdResponse deleteBookByIdResponse(DeleteBookByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        Long id = request.getBookIdToDelete();
        if (!errors.isEmpty()) {
            return new DeleteBookByIdResponse(errors);
        } else return new DeleteBookByIdResponse(electronicLibrary.deleteBookById(id));
    }
}

