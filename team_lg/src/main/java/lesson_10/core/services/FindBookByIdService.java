package lesson_10.core.services;

import lesson_10.core.database.ElectronicLibrary;
import lesson_10.core.requests.FindBookByIdRequest;
import lesson_10.core.responses.CoreError;
import lesson_10.core.responses.FindBookByIdResponse;
import lesson_10.core.services.validators.FindBookByIdValidator;
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
