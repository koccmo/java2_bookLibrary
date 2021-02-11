package book_library.core.services.Reader;

import book_library.core.database.Reader.ReaderRepository;
import book_library.core.domain.Reader;
import book_library.core.requests.Reader.GetAllReaderRequest;
import book_library.core.responses.Reader.GetAllReadersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetAllReadersService {

    @Autowired
    private ReaderRepository readerRepository;

    public GetAllReadersResponse execute (GetAllReaderRequest request){
        List<Reader> readers = readerRepository.getAllReaders();
        return new GetAllReadersResponse(readers);
    }
}
