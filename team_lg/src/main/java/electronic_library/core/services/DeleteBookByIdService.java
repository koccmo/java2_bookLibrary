package electronic_library.core.services;

import electronic_library.core.database.ElectronicLibraryRepository;
import electronic_library.core.requests.DeleteBookByIdRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.DeleteBookByIdResponse;
import electronic_library.core.services.validators.DeleteBookByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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

