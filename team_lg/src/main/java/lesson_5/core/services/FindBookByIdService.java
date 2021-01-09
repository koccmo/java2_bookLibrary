package lesson_5.core.services;

import lesson_5.core.database.ElectronicLibrary;
import lesson_5.core.requests.FindBookByIdRequest;
import lesson_5.core.responses.CoreError;
import lesson_5.core.responses.FindBookByIdResponse;
import lesson_5.core.services.validators.FindBookByIdValidator;

import java.util.List;

public class FindBookByIdService {

    private final ElectronicLibrary electronicLibrary;
    private final FindBookByIdValidator validator;

    public FindBookByIdService(ElectronicLibrary electronicLibrary, FindBookByIdValidator validator) {
        this.electronicLibrary = electronicLibrary;
        this.validator = validator;
    }

    public FindBookByIdResponse findBookByIdResponse(FindBookByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindBookByIdResponse(errors);
        }
        Long id = Long.parseLong(request.getBookId());
        return new FindBookByIdResponse(electronicLibrary.findBookById(id));
    }
}
