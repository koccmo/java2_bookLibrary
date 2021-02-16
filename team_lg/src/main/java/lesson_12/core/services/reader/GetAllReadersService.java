package lesson_12.core.services.reader;

import lesson_12.core.database.reader.ReaderRepository;
import lesson_12.core.domain.Reader;
import lesson_12.core.requests.reader.GetAllReadersRequest;
import lesson_12.core.responses.reader.GetAllReadersResponse;
import lesson_12.core.services.reader.validators.GetAllReadersValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetAllReadersService {
    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private GetAllReadersValidator getAllReadersValidator;

    public GetAllReadersResponse execute(GetAllReadersRequest request) {
        List<Reader> readers = readerRepository.getReaders();
        return new GetAllReadersResponse(readers);
    }
}
