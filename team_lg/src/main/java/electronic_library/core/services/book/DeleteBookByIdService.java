package electronic_library.core.services.book;

import electronic_library.core.database.book.ElectronicLibraryRepository;
import electronic_library.core.requests.book.DeleteBookByIdRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.book.DeleteBookByIdResponse;
import electronic_library.core.services.book.validators.DeleteBookByIdValidator;
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

