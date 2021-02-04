package electronic_library.core.services.reader;

import electronic_library.core.database.reader.ReaderRepository;
import electronic_library.core.domain.Reader;
import electronic_library.core.requests.reader.GetAllReadersRequest;
import electronic_library.core.responses.reader.GetAllReadersResponse;
import electronic_library.core.services.reader.validators.GetAllReadersValidator;
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
