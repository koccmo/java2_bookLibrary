package lesson_12.core.services.reader;

import lesson_12.core.database.reader.ReaderRepository;
import lesson_12.core.requests.reader.FindReaderByIdRequest;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.reader.FindReaderByIdResponse;
import lesson_12.core.services.reader.validators.FindReaderByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class FindReaderByIdService {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private FindReaderByIdValidator validator;

    public FindReaderByIdResponse findReaderByIdResponse(FindReaderByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindReaderByIdResponse(errors);
        }
        Long id = Long.parseLong(request.getReaderId());
        return new FindReaderByIdResponse(readerRepository.findReaderById(id));
    }

}
