package lesson_12.core.services.book;

import lesson_12.core.database.book.ElectronicLibraryRepository;
import lesson_12.core.requests.book.DeleteBookByIdRequest;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.book.DeleteBookByIdResponse;
import lesson_12.core.services.book.validators.DeleteBookByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DeleteBookByIdService {

    @Autowired
    private ElectronicLibraryRepository electronicLibrary;

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

