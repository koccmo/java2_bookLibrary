package electronic_library.core.services.book;

import electronic_library.core.database.book.ElectronicLibraryRepository;
import electronic_library.core.requests.book.DeleteBookByAuthorRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.book.DeleteBookByAuthorResponse;
import electronic_library.core.services.book.validators.DeleteBookByAuthorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DeleteBookByAuthorService {

    @Autowired
    private ElectronicLibraryRepository electronicLibrary;

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
