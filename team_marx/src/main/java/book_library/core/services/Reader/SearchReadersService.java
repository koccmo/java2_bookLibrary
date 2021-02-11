package book_library.core.services.Reader;

import book_library.core.database.Reader.ReaderRepository;
import book_library.core.domain.Reader;
import book_library.core.requests.Reader.SearchReaderRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.Reader.SearchReadersResponse;
import book_library.core.validators.Reader.SearchReadersRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class SearchReadersService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private SearchReadersRequestValidator validator;


    public SearchReadersResponse execute(SearchReaderRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()){
            return new SearchReadersResponse(null, errors);
        }
        List<Reader> readers = new ArrayList<>();
        return new SearchReadersResponse(readers, null);
    }
}
