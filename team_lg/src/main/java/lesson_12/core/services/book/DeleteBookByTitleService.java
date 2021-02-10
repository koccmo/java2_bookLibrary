package lesson_12.core.services.book;

import lesson_12.core.database.book.ElectronicLibraryRepository;
import lesson_12.core.requests.book.DeleteBookByTitleRequest;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.book.DeleteBookByTitleResponse;
import lesson_12.core.services.book.validators.DeleteBookByTitleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
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
