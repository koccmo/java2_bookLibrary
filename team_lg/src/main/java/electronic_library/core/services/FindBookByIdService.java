package electronic_library.core.services;

import electronic_library.core.database.ElectronicLibrary;
import electronic_library.core.requests.FindBookByIdRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.FindBookByIdResponse;
import electronic_library.core.services.validators.FindBookByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindBookByIdService {

    @Autowired
    private ElectronicLibrary electronicLibrary;

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
