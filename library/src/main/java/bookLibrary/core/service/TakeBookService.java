package bookLibrary.core.service;

import bookLibrary.core.dataBase.ReaderBookRepository;
import bookLibrary.core.domain.Book;
import bookLibrary.core.domain.Reader;
import bookLibrary.core.domain.ReaderBook;
import bookLibrary.core.request.TakeBookRequest;
import bookLibrary.core.response.CoreError;
import bookLibrary.core.response.TakeBookResponse;
import bookLibrary.core.service.validators.TakeBookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class TakeBookService {

    @Autowired
    private TakeBookValidator validator;
    @Autowired
    private ReaderBookRepository repository;

    public TakeBookResponse execute(TakeBookRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            Reader reader = repository.readerGetById(Long.valueOf(request.getReaderId()));
            Book book = repository.bookGetById(Long.valueOf(request.getBookId()));
            repository.save(new ReaderBook(reader, book, request.getWhenBookTaken()));
            return new TakeBookResponse(true);
        }
        return new TakeBookResponse(errors);
    }
}
