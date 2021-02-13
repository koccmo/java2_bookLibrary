package book_library.core.services.Reader;

import book_library.core.database.Reader.ReaderRepository;
import book_library.core.domain.Book;
import book_library.core.domain.Reader;
import book_library.core.requests.Book.SearchBooksRequest;
import book_library.core.requests.Ordering;
import book_library.core.requests.Reader.SearchReaderRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.Reader.SearchReadersResponse;
import book_library.core.validators.Book.SearchBooksRequestValidator;
import book_library.core.validators.Reader.SearchReadersRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        if (!errors.isEmpty()) {
            return new SearchReadersResponse(null, errors);
        }
        List<Reader> readers = new ArrayList<>();
        return new SearchReadersResponse(readers, null);
    }

    private List<Reader> order(List<Reader> readers, Ordering ordering) {
        if (orderingEnabled && SearchBooksRequestValidator.isOrderingRequested(ordering)) {
            Comparator<Reader> comparator = null;
            if (ordering.getOrderBy().equals("firstName")) {
                comparator = Comparator.comparing(Reader::getFirstName);
            } else if (ordering.getOrderBy().equals("lastName")) {
                comparator = Comparator.comparing(Reader::getLastName);
            } else {
                comparator = Comparator.comparing(Reader::getPersonalCode);
            }
//            Comparator<Reader> comparator = if(ordering.getOrderBy().equals("firstName")){
//                        Comparator.comparing(Reader::getFirstName);
//                    } else {Comparator.comparing(Reader::getFirstName);};

                if (ordering.getOrderDirection().equals("DESCENDING")) {
                    comparator = comparator.reversed();
                }
            return readers.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return readers;
        }
    }

}
