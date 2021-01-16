package electronic_library.core.services;

import electronic_library.core.database.ElectronicLibrary;
import electronic_library.core.requests.DeleteBookByAuthorRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.DeleteBookByAuthorResponse;
import electronic_library.core.services.validators.DeleteBookByAuthorValidator;
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
