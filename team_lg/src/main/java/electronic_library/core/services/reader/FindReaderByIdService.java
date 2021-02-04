package electronic_library.core.services.reader;

import electronic_library.core.database.reader.ReaderRepository;
import electronic_library.core.requests.reader.FindReaderByIdRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.reader.FindReaderByIdResponse;
import electronic_library.core.services.reader.validators.FindReaderByIdValidator;
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
