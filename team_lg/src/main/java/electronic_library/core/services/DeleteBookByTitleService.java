package electronic_library.core.services;

import electronic_library.core.database.ElectronicLibraryRepository;
import electronic_library.core.requests.DeleteBookByTitleRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.DeleteBookByTitleResponse;
import electronic_library.core.services.validators.DeleteBookByTitleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteBookByTitleService {

    @Autowired
    private ElectronicLibraryRepository electronicLibrary;

    @Autowired
    private DeleteBookByTitleValidator validator;

    public DeleteBookByTitleResponse deleteBookByTitleResponse(DeleteBookByTitleRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteBookByTitleResponse(errors);
        }
        boolean isRemoved = electronicLibrary.deleteBookByTitle(request.getBookTitle());
        return new DeleteBookByTitleResponse(isRemoved);
    }
}
