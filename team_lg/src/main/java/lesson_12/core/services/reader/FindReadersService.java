package lesson_12.core.services.reader;

import lesson_12.core.database.reader.ReaderRepository;
import lesson_12.core.domain.Reader;
import lesson_12.core.requests.Ordering;
import lesson_12.core.requests.Paging;
import lesson_12.core.requests.reader.FindReadersRequest;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.reader.FindReadersResponse;
import lesson_12.core.services.reader.validators.FindReadersRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class FindReadersService {
    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private FindReadersRequestValidator validator;

    public FindReadersResponse execute(FindReadersRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindReadersResponse(null,errors);
        }

        List<Reader> Readers = find(request);
        Readers = order(Readers, request.getOrdering());
        Readers = paging(Readers, request.getPaging());

        return new FindReadersResponse(Readers, null);
    }

    private List<Reader> order(List<Reader> Readers, Ordering ordering) {
        if (ordering != null) {
            Comparator<Reader> comparator = ordering.getOrderBy().equals("firstName")
                    ? Comparator.comparing(Reader::getReaderFirstName)
                    : Comparator.comparing(Reader::getReaderLastName);
            if (ordering.getOrderDirection().equals("DESC")) {
                comparator = comparator.reversed();
            }
            return Readers.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return Readers;
        }
    }

    private List<Reader> find(FindReadersRequest request) {
        List<Reader> Readers = new ArrayList<>();
        if (request.isReaderFirstNameProvided() && !request.isReaderLastNameProvided()) {
            Readers = readerRepository.findReaderByFirstName(request.getReaderFirstName());
        }
        if (!request.isReaderFirstNameProvided() && request.isReaderLastNameProvided()) {
            Readers = readerRepository.findReaderByLastName(request.getReaderLastName());
        }
        if (request.isReaderFirstNameProvided() && request.isReaderLastNameProvided()) {
            Readers = readerRepository.findByFirstNameAndLastName(request.getReaderFirstName(), request.getReaderLastName());
        }
        if (!request.isReaderFirstNameProvided() && !request.isReaderLastNameProvided() && request.isReaderPersonalCodeProvided()) {
            Readers = readerRepository.findReaderByPersonalCode(request.getReaderPersonalCode());
        }
        return Readers;
    }

    private List<Reader> paging(List<Reader> Readers, Paging paging) {
        if (paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return Readers.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return Readers;
        }
    }
}
