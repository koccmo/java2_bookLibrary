package bookLibrary.core.service;

import bookLibrary.core.dataBase.ReaderRepository;
import bookLibrary.core.domain.Reader;
import bookLibrary.core.request.Ordering;
import bookLibrary.core.request.Paging;
import bookLibrary.core.request.SearchReaderRequest;
import bookLibrary.core.response.CoreError;
import bookLibrary.core.response.SearchReaderResponse;
import bookLibrary.core.service.validators.ReaderSearchFieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class ReaderSearchService {
    @Autowired
    private ReaderSearchFieldValidator validator;
    @Autowired
    private ReaderRepository dataBase;

    @Value("${reader.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${reader.paging.enabled}")
    private boolean pagingEnabled;

    public SearchReaderResponse execute(SearchReaderRequest request) {
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()) {
            return new SearchReaderResponse(errors, null);
        }
        List<Reader> readers = readerSearch(request);
        readers = readersOrdering(readers, request.getOrdering());
        readers = readersPaging(readers, request.getPaging());
        return new SearchReaderResponse(null, readers);
    }

    private List<Reader> readerSearch(SearchReaderRequest request) {
        List<Reader> readers = null;

        if(!request.getName().isEmpty() && request.getLastName().isEmpty()) {
            readers = dataBase.findByName(request.getName());
        }
        if(request.getName().isEmpty() && !request.getLastName().isEmpty()) {
            readers = dataBase.findByLastName(request.getLastName());
        }
        if(!request.getName().isEmpty() && !request.getLastName().isEmpty()) {
            readers = dataBase.findByNameAndLastName(request.getName(), request.getLastName());
        }
        return readers;
    }

    private List<Reader> readersOrdering(List<Reader> readers, Ordering ordering) {
        if(orderingEnabled && ordering != null) {
            Comparator<Reader> comparator = ordering.getOrderBy().equalsIgnoreCase("Name")
                    ? Comparator.comparing(Reader::getFirstName)
                    : Comparator.comparing(Reader::getLastName);
            if(ordering.getOrderDirection().equalsIgnoreCase("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return readers.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return readers;
        }

    }

    private List<Reader> readersPaging(List<Reader> readers, Paging paging) {
        if(pagingEnabled && paging != null) {
            int skip = (paging.getPageNumber() -1) * paging.getPageSize();
            return readers.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else return readers;
    }
}
