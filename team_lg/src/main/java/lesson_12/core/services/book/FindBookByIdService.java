package lesson_12.core.services.book;

import lesson_12.core.database.book.ElectronicLibraryRepository;
import lesson_12.core.requests.book.FindBookByIdRequest;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.book.FindBookByIdResponse;
import lesson_12.core.services.book.validators.FindBookByIdValidator;
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
