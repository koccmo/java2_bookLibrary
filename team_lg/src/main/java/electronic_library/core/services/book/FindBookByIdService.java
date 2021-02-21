package electronic_library.core.services.book;

import electronic_library.core.database.book.ElectronicLibraryRepository;
import electronic_library.core.requests.book.FindBookByIdRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.book.FindBookByIdResponse;
import electronic_library.core.services.book.validators.FindBookByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class FindBookByIdService {

    @Autowired
    private ElectronicLibraryRepository electronicLibrary;

    @Autowired
    private FindBookByIdValidator validator;

    public FindBookByIdResponse findBookByIdResponse(FindBookByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindBookByIdResponse(errors);
        }
        Long id = Long.parseLong(request.getBookId());
        return new FindBookByIdResponse(electronicLibrary.findBookById(id));
    }
}
