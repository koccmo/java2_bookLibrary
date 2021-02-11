package book_library.core.services.Reader;

import book_library.core.database.Reader.ReaderRepository;
import book_library.core.services.Book.SearchBooksService;
import book_library.core.validators.Reader.SearchReadersRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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



}
