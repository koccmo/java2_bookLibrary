package book_library.core.services.Reader;

import book_library.core.database.Reader.ReaderRepository;
import book_library.core.domain.Reader;
import book_library.core.requests.Ordering;
import book_library.core.requests.Paging;
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
        List<Reader> readers = search(request);
        readers = order(readers, request.getOrdering());
        readers = paging(readers, request.getPaging());

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

            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return readers.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return readers;
        }
    }

    private List<Reader> search(SearchReaderRequest request) {
        List<Reader> readers = new ArrayList<>();
        if (request.isFirstNameProvided() && !request.isLastNameProvided() && !request.isPersonalCodeProvided()) {
            readers = readerRepository.findByFirstName(request.getFirstName());
        }
        if (!request.isFirstNameProvided() && request.isLastNameProvided() && !request.isPersonalCodeProvided()) {
            readers = readerRepository.findByLastName(request.getLastName());
        }

        if (!request.isFirstNameProvided() && !request.isLastNameProvided() && request.isPersonalCodeProvided()) {
            readers = readerRepository.findByPersonalCode(request.getPersonalCode());
        }

        if (request.isFirstNameProvided() && request.isLastNameProvided() && !request.isPersonalCodeProvided()) {
            readers = readerRepository.findByFirstNameAndLastName(request.getFirstName(), request.getLastName());
        }
        if (request.isFirstNameProvided() && !request.isLastNameProvided() && request.isPersonalCodeProvided()) {
            readers = readerRepository.findByFirstNameAndPersonalCode(request.getFirstName(), request.getPersonalCode());
        }

        if (!request.isFirstNameProvided() && request.isLastNameProvided() && request.isPersonalCodeProvided()) {
            readers = readerRepository.findByLastNameAndPersonalCode(request.getLastName(), request.getPersonalCode());
        }

        if (request.isFirstNameProvided() && request.isLastNameProvided() && request.isPersonalCodeProvided()) {
            readers = readerRepository.findByFirstNameAndLastNameAndPersonalCode(request.getFirstName(), request.getLastName(), request.getPersonalCode());
        }
        return readers;
    }

    private List<Reader> paging(List<Reader> readers, Paging paging) {
        if (pagingEnabled && SearchBooksRequestValidator.isPagingRequested(paging)) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return readers.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return readers;
        }
    }

}
